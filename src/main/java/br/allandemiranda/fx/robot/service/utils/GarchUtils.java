package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.provider.Candlestick;
import br.allandemiranda.fx.robot.dto.provider.Garch;
import br.allandemiranda.fx.robot.dto.provider.GarchForecast;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.OrderType;
import br.allandemiranda.fx.robot.model.input.GarchInput;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.finmath.timeseries.models.parametric.GARCH;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Slf4j
@UtilityClass
public class GarchUtils {

  private static final Object GARCH_LOCK = new Object();

  @Contract(pure = true)
  private static double[] filterSigma(double omega, double alpha, double beta, double[] r) {
    int n = r.length;
    double[] sigma = new double[n];

    double variance = GarchUtils.sampleVariance(r);
    double sigma2 = Math.max(1e-18, variance);

    for (int t = 0; t < n; t++) {
      double rPrev2 = (t == 0) ? variance : r[t - 1] * r[t - 1];
      sigma2 = omega + alpha * rPrev2 + beta * sigma2;
      sigma2 = Math.max(1e-18, sigma2);
      sigma[t] = Math.sqrt(sigma2);
    }
    return sigma;
  }

  @Contract(pure = true)
  private static double[] forecastSigma(double omega, double alpha, double beta, double lastR, double lastSigma, int horizon) {
    double[] out = new double[horizon];
    double lastSigma2 = lastSigma * lastSigma;

    // 1-step uses last observed return
    double s2 = omega + alpha * (lastR * lastR) + beta * lastSigma2;
    out[0] = Math.sqrt(Math.max(1e-18, s2));

    // Multi-step recursive forecast: s2 = omega + (alpha + beta) * s2
    double phi = alpha + beta;
    for (int i = 1; i < horizon; i++) {
      s2 = omega + phi * s2;
      out[i] = Math.sqrt(Math.max(1e-18, s2));
    }
    return out;
  }

  @Contract(pure = true)
  private static double get(Map<String, Object> map, String key) {
    for (var e : map.entrySet()) {
      if (e.getKey().equalsIgnoreCase(key)) {
        Object v = e.getValue();
        if (v instanceof Number n) {
          return n.doubleValue();
        }
        if (v instanceof String s) {
          return Double.parseDouble(s);
        }
      }
    }
    throw new IllegalStateException("Missing key: " + key + " in " + map.keySet());
  }

  @Contract(pure = true)
  private static Garch getGarch11ModuleForecast(double[] prices, int horizon) {
    Objects.requireNonNull(prices);
    if (horizon < 1) {
      throw new IllegalArgumentException("horizon must be >= 1");
    }
    if (prices.length < 50) {
      throw new IllegalArgumentException("candlesticks size too short (use >= 50)");
    }

    // 1) Estimate parameters using finmath (operates on log-returns)
    GARCH garch = new GARCH(prices);
    Map<String, Object> best;
    synchronized (GARCH_LOCK) {
      best = garch.getBestParameters();
    }

    double omega = GarchUtils.get(best, "omega");
    double alpha = GarchUtils.get(best, "alpha");
    double beta = GarchUtils.get(best, "beta");

    // 2) Compute log returns: r_t = ln(P_t / P_{t-1})
    double[] r = GarchUtils.logReturns(prices);

    // 3) Filter in-sample sigma_t (standard GARCH(1,1): sigma_t^2 = omega + alpha*r_{t-1}^2 + beta*sigma_{t-1}^2)
    double[] sigma = GarchUtils.filterSigma(omega, alpha, beta, r);

    double lastSigma = sigma[sigma.length - 1];
    double lastR = r[r.length - 1];

    // 4) Multi-step forecast sigma[t+1..t+h]
    double[] sigmaNext = GarchUtils.forecastSigma(omega, alpha, beta, lastR, lastSigma, horizon);

    // 5) Aggregated volatility over horizon h: sigmaAgg = sqrt(sum(sigmaNext_i^2))
    double sumSq = 0.0;
    for (double s : sigmaNext) {
      sumSq += s * s;
    }
    double sigmaAgg = Math.sqrt(Math.max(1e-18, sumSq));

    return new Garch() {
      @Override
      public double alpha() {
        return alpha;
      }

      @Override
      public double beta() {
        return beta;
      }

      @Override
      public double omega() {
        return omega;
      }

      @Override
      public double sigmaAgg() {
        return sigmaAgg;
      }
    };
  }

  @Contract(pure = true)
  private static double[] logReturns(double[] prices) {
    double[] r = new double[prices.length - 1];
    for (int i = 1; i < prices.length; i++) {
      r[i - 1] = Math.log(prices[i] / prices[i - 1]);
    }
    return r;
  }

  @Contract(pure = true)
  private static double sampleVariance(double[] x) {
    double mean = 0.0;
    for (double v : x) {
      mean += v;
    }
    mean /= x.length;

    double s = 0.0;
    for (double v : x) {
      double d = v - mean;
      s += d * d;
    }
    return s / Math.max(1, x.length - 1);
  }

  @Contract(pure = true)
  public static Function<@Unmodifiable List<? extends Candlestick>, Stream<GarchForecast>> getGarchForecasts(GarchInput garchInput, OrderType orderType) {
    log.info("getGarchForecasts(): [garchInput={}]", garchInput);

    return candlesticks -> {
      int windowCount = candlesticks.size() - garchInput.priceSize() + 1;
      if (windowCount <= 0) {
        throw new IllegalStateException("Insufficient candlesticks to calculate GarchStationarity Forecasts, current size of full list for the scope: " + candlesticks.size());
      }

      return IntStream.range(0, windowCount)
          .parallel()
          .mapToObj(i -> candlesticks.subList(i, i + garchInput.priceSize()))
          .map(candlestickDtoList -> {
            log.info("getGarchForecasts(): [garchInput={}], computing GarchStationarity Forecast for [horizon={}, candlesticks.size={}]", garchInput, garchInput.horizon(), candlestickDtoList.size());
            Candlestick candlesticksReference = candlestickDtoList.getLast();
            double[] prices = candlestickDtoList.stream().mapToDouble(candlestick -> AppliedPrice.PRICE_CLOSE.getPrice(candlestick).doubleValue()).toArray();

            Garch garch = GarchUtils.getGarch11ModuleForecast(prices, garchInput.horizon());
            log.info("getGarchForecasts(): [garchInput={}], GarchStationarity Forecast Computed [garch={}] for [horizon={}, candlesticks.size={}]", garchInput, garch, garchInput.horizon(), candlestickDtoList.size());

            return new GarchForecast() {
              @Override
              public double alpha() {
                return garch.alpha();
              }

              @Override
              public double beta() {
                return garch.beta();
              }

              @Override
              public double omega() {
                return garch.omega();
              }

              @Override
              public OrderType orderType() {
                return orderType;
              }

              @Override
              public BigDecimal price() {
                return AppliedPrice.PRICE_CLOSE.getPrice(candlesticksReference);
              }

              @Override
              public double sigmaAgg() {
                return garch.sigmaAgg();
              }

              @Override
              public OffsetDateTime timestamp() {
                return candlesticksReference.timestamp();
              }
            };
          });
    };
  }
}

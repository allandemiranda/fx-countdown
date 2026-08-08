package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.analysis.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.core.Candlestick;
import br.allandemiranda.fx.robot.dto.core.CandlestickDto;
import br.allandemiranda.fx.robot.dto.impl.input.GarchInputDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.PositionType;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import net.finmath.timeseries.models.parametric.GARCH;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

@Log4j2
@UtilityClass
public class GarchUtils {

  private static GarchUtils.GarchForecast getGarch11ModuleForecast(double[] prices, int horizon) {
    Objects.requireNonNull(prices);
    if (horizon < 1) {
      throw new IllegalArgumentException("horizon must be >= 1");
    }
    if (prices.length < 50) {
      throw new IllegalArgumentException("candlesticks size too short (use >= 50)");
    }

    // 1) estima parâmetros com finmath (ele trabalha sobre log-returns dos values)
    GARCH garch = new GARCH(prices);
    Map<String, Object> best = garch.getBestParameters();

    double omega = GarchUtils.get(best, "omega");
    double alpha = GarchUtils.get(best, "alpha");
    double beta = GarchUtils.get(best, "beta");

    // 2) calcula log-returns (r_t)
    double[] r = GarchUtils.logReturns(prices);

    // 3) filtra sigma_t in-sample (GARCH(1,1) padrão)
    //    sigma_t^2 = omega + alpha*r_{t-1}^2 + beta*sigma_{t-1}^2
    double[] sigma = GarchUtils.filterSigma(omega, alpha, beta, r);

    double lastSigma = sigma[sigma.length - 1];
    double lastR = r[r.length - 1];

    // 4) forecast sigma[t+1..t+h]
    double[] sigmaNext = GarchUtils.forecastSigma(omega, alpha, beta, lastR, lastSigma, horizon);

    // 5) sigma agregado (para horizonte h)
    double sumSq = 0.0;
    for (double s : sigmaNext) {
      sumSq += s * s;
    }
    double sigmaAgg = Math.sqrt(Math.max(1e-18, sumSq));

    return new GarchUtils.GarchForecast(omega, alpha, beta, sigmaAgg);
  }

  private static double[] logReturns(double[] prices) {
    double[] r = new double[prices.length - 1];
    for (int i = 1; i < prices.length; i++) {
      r[i - 1] = Math.log(prices[i] / prices[i - 1]);
    }
    return r;
  }

  private static double[] filterSigma(double omega, double alpha, double beta, double[] r) {
    int n = r.length;
    double[] sigma = new double[n];

    // inicialização simples: variância amostral dos retornos
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

  private static double[] forecastSigma(double omega, double alpha, double beta, double lastR, double lastSigma, int horizon) {
    double[] out = new double[horizon];
    double lastSigma2 = lastSigma * lastSigma;

    // 1-step usa o último retorno observado
    double s2 = omega + alpha * (lastR * lastR) + beta * lastSigma2;
    out[0] = Math.sqrt(Math.max(1e-18, s2));

    // multi-step: assume E[r^2] = sigma^2 -> recursão: s2 = omega + (alpha+beta)*s2
    double phi = alpha + beta;
    for (int i = 1; i < horizon; i++) {
      s2 = omega + phi * s2;
      out[i] = Math.sqrt(Math.max(1e-18, s2));
    }
    return out;
  }

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

  private static double get(Map<String, Object> map, String key) {
    for (var e : map.entrySet()) {
      if (e.getKey() != null && e.getKey().equalsIgnoreCase(key)) {
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

  public static <C extends Candlestick> Function<List<? extends C>, ParallelFlux<GarchForecastDto>> getGarchForecasts(GarchInputDto garchInputDto, PositionType positionType) {
    GarchUtils.log.info("getGarchForecasts(): [garchInputDto={}]", garchInputDto);

    return candlestickDtos -> {
      if ((candlestickDtos.size() - garchInputDto.priceSize() + 1) <= 0) {
        return Flux.<GarchForecastDto>error(() -> new IllegalStateException("Insufficient candlesticks to calculate Garch Forecasts, current size of full list for the scope: " + candlestickDtos.size())).parallel();
      } else {
        return Flux.range(0, candlestickDtos.size() - garchInputDto.priceSize() + 1).parallel().runOn(Schedulers.parallel()).map(i -> candlestickDtos.subList(i, i + garchInputDto.priceSize()))
            .flatMap(candlestickDtoList -> {
              if (candlestickDtoList.size() < garchInputDto.priceSize()) {
                return Mono.error(() -> new IllegalStateException("Insufficient candlesticks to calculate Garch Forecasts, current sub size: " + candlestickDtoList.size()));
              } else {
                GarchUtils.log.info("getGarchForecasts(): [garchInputDto={}], computing Garch Forecast for [horizon={}, candlesticks.size={}]", garchInputDto, garchInputDto.horizon(), candlestickDtoList.size());
                C candlesticksReference = candlestickDtoList.getLast();
                double[] prices = candlestickDtoList.stream().mapToDouble(candlestick -> AppliedPrice.PRICE_CLOSE.getPrice(candlestick).doubleValue()).toArray();
                GarchUtils.GarchForecast garchForecast = GarchUtils.getGarch11ModuleForecast(prices, garchInputDto.horizon());
                GarchUtils.log.info("getGarchForecasts(): [garchInputDto={}], Garch Forecast Computed [garchForecast={}] for [horizon={}, candlesticks.size={}]", garchInputDto, garchForecast, garchInputDto.horizon(),
                    candlestickDtoList.size());
                return Mono.just(new GarchForecastDto(candlesticksReference.timestamp(), AppliedPrice.PRICE_CLOSE.getPrice(candlesticksReference), positionType, garchForecast.omega(), garchForecast.alpha(), garchForecast.beta(),
                    garchForecast.sigmaAgg()));
              }
            });
      }
    };
  }

  private record GarchForecast(double omega,
                               double alpha,
                               double beta,
                               double sigmaAgg) // sqrt(sum sigmaNext^2)
  {

  }
}

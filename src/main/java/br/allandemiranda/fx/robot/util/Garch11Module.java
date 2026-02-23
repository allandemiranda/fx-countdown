package br.allandemiranda.fx.robot.util;

import java.util.Map;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import net.finmath.timeseries.models.parametric.GARCH;

@UtilityClass
public final class Garch11Module {

  public static GarchForecast fitAndForecast(double[] close, int horizon) {
    Objects.requireNonNull(close);
    if (horizon < 1) {
      throw new IllegalArgumentException("horizon must be >= 1");
    }
    if (close.length < 50) {
      throw new IllegalArgumentException("close length too short (use >= 50)");
    }
    for (double v : close) {
      if (v <= 0) {
        throw new IllegalArgumentException("close must be > 0");
      }
    }

    // 1) estima parâmetros com finmath (ele trabalha sobre log-returns dos values)
    GARCH garch = new GARCH(close);
    Map<String, Object> best = garch.getBestParameters();

    double omega = get(best, "omega");
    double alpha = get(best, "alpha");
    double beta = get(best, "beta");

    // 2) calcula log-returns (r_t)
    double[] r = logReturns(close);

    // 3) filtra sigma_t in-sample (GARCH(1,1) padrão)
    //    sigma_t^2 = omega + alpha*r_{t-1}^2 + beta*sigma_{t-1}^2
    double[] sigma = filterSigma(omega, alpha, beta, r);

    double lastSigma = sigma[sigma.length - 1];
    double lastR = r[r.length - 1];

    // 4) forecast sigma[t+1..t+h]
    double[] sigmaNext = forecastSigma(omega, alpha, beta, lastR, lastSigma, horizon);

    // 5) sigma agregado (para horizonte h)
    double sumSq = 0.0;
    for (double s : sigmaNext) {
      sumSq += s * s;
    }
    double sigmaAgg = Math.sqrt(Math.max(1e-18, sumSq));

    return new GarchForecast(omega, alpha, beta, sigmaAgg);
  }

  private static double[] logReturns(double[] close) {
    double[] r = new double[close.length - 1];
    for (int i = 1; i < close.length; i++) {
      r[i - 1] = Math.log(close[i] / close[i - 1]);
    }
    return r;
  }

  private static double[] filterSigma(double omega, double alpha, double beta, double[] r) {
    int n = r.length;
    double[] sigma = new double[n];

    // inicialização simples: variância amostral dos retornos
    double var = sampleVariance(r);
    double sigma2 = Math.max(1e-18, var);

    for (int t = 0; t < n; t++) {
      double rPrev2 = (t == 0) ? var : r[t - 1] * r[t - 1];
      sigma2 = omega + alpha * rPrev2 + beta * sigma2;
      sigma2 = Math.max(1e-18, sigma2);
      sigma[t] = Math.sqrt(sigma2);
    }
    return sigma;
  }

  private static double[] forecastSigma(double omega, double alpha, double beta,
      double lastR, double lastSigma, int horizon) {
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

  public record GarchForecast(
      double omega,
      double alpha,
      double beta,
      double sigmaAgg      // sqrt(sum sigmaNext^2)
  ) {

  }
}

package br.allandemiranda.fx.robot.util;

import br.allandemiranda.fx.robot.enums.PositionType;
import java.math.BigDecimal;
import java.math.MathContext;
import lombok.experimental.UtilityClass;
import org.jspecify.annotations.NonNull;

@UtilityClass
public final class RiskLevelCalculator {

  private static final MathContext MC = MathContext.DECIMAL64;

  public static @NonNull LevelPrice fromSigmaAgg(@NonNull BigDecimal price, @NonNull BigDecimal pipSize, @NonNull PositionType positionType, @NonNull BigDecimal sigmaAgg, @NonNull BigDecimal kSL, @NonNull BigDecimal kTP) {

    if (price.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("price must be > 0");
    }
    if (pipSize.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("pipSize must be > 0");
    }
    if (sigmaAgg.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("sigmaAgg invalid");
    }
    if (kSL.compareTo(BigDecimal.ZERO) <= 0 || kTP.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("kSL/kTP must be > 0");
    }

    BigDecimal move1SigmaPrice = price.multiply(sigmaAgg, MC);
    BigDecimal move1SigmaPips = move1SigmaPrice.divide(pipSize, MC);

    BigDecimal slPips = kSL.multiply(move1SigmaPips, MC);
    BigDecimal tpPips = kTP.multiply(move1SigmaPips, MC);

    return switch (positionType) {
      case POSITION_TYPE_BUY -> {
        BigDecimal slPrice = price.subtract(slPips.multiply(pipSize, MC), MC);
        BigDecimal tpPrice = price.add(tpPips.multiply(pipSize, MC), MC);
        yield new LevelPrice(tpPrice, slPrice);
      }
      case POSITION_TYPE_SELL -> {
        BigDecimal slPrice = price.add(slPips.multiply(pipSize, MC), MC);
        BigDecimal tpPrice = price.subtract(tpPips.multiply(pipSize, MC), MC);
        yield new LevelPrice(tpPrice, slPrice);
      }
    };
  }

  public record LevelPrice(BigDecimal tpPrice, BigDecimal slPrice) {

  }
}

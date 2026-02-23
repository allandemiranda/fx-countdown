package br.allandemiranda.fx.robot.util;

import br.allandemiranda.fx.robot.enums.PositionType;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import org.jspecify.annotations.NonNull;

@UtilityClass
public final class RiskLevelCalculator {

  public static @NonNull LevelPrice fromSigmaAgg(double price, double pipSize, PositionType positionType, double sigmaAgg, double kSL, double kTP) {
    Objects.requireNonNull(positionType);
    if (price <= 0) {
      throw new IllegalArgumentException("price must be > 0");
    }
    if (pipSize <= 0) {
      throw new IllegalArgumentException("pipSize must be > 0");
    }
    if ((sigmaAgg <= 0) || !Double.isFinite(sigmaAgg)) {
      throw new IllegalArgumentException("sigmaAgg invalid");
    }
    if ((kSL <= 0) || (kTP <= 0)) {
      throw new IllegalArgumentException("kSL/kTP must be > 0");
    }

    double move1SigmaPrice = price * sigmaAgg;
    double move1SigmaPips = move1SigmaPrice / pipSize;

    double slPips = kSL * move1SigmaPips;
    double tpPips = kTP * move1SigmaPips;

    return switch (positionType) {
      case POSITION_TYPE_BUY -> {
        double slPrice = price - slPips * pipSize;
        double tpPrice = price + tpPips * pipSize;
        yield new RiskLevelCalculator.LevelPrice(tpPrice, slPrice);
      }
      case POSITION_TYPE_SELL -> {
        double slPrice = price + slPips * pipSize;
        double tpPrice = price - tpPips * pipSize;
        yield new RiskLevelCalculator.LevelPrice(tpPrice, slPrice);
      }
    };
  }

  public record LevelPrice(double tpPrice, double slPrice) {

  }
}

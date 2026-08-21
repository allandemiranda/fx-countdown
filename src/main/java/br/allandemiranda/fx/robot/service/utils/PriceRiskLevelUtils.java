package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.provider.GarchForecast;
import br.allandemiranda.fx.robot.dto.provider.PriceRiskLevel;
import br.allandemiranda.fx.robot.enums.OrderType;
import br.allandemiranda.fx.robot.model.core.SymbolParameters;
import br.allandemiranda.fx.robot.model.input.PriceRiskLevelInput;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.OffsetDateTime;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Slf4j
@UtilityClass
public final class PriceRiskLevelUtils {

  private static final MathContext MC = MathContext.DECIMAL64;

  @Contract(pure = true)
  private static PriceRiskLevel createPriceRiskLevel(OffsetDateTime timestamp, OrderType orderType, BigDecimal tpPrice, BigDecimal slPrice) {
    return new PriceRiskLevel() {
      @Override
      public OrderType orderType() {
        return orderType;
      }

      @Override
      public BigDecimal slPrice() {
        return slPrice;
      }

      @Override
      public OffsetDateTime timestamp() {
        return timestamp;
      }

      @Override
      public BigDecimal tpPrice() {
        return tpPrice;
      }
    };
  }

  @Contract(pure = true)
  private static PriceRiskLevel fromSigmaAgg(BigDecimal price, GarchForecast garchForecast, SymbolParameters symbolParameters, PriceRiskLevelInput priceRiskLevelInput) {
    BigDecimal move1SigmaPrice = price.multiply(BigDecimal.valueOf(garchForecast.sigmaAgg()), MC);
    BigDecimal move1SigmaPips = move1SigmaPrice.divide(symbolParameters.point(), MC);

    BigDecimal slPips = priceRiskLevelInput.kSL().multiply(move1SigmaPips, MC);
    BigDecimal tpPips = priceRiskLevelInput.kTP().multiply(move1SigmaPips, MC);

    return switch (garchForecast.orderType()) {
      case ORDER_TYPE_BUY -> {
        BigDecimal slPrice = price.subtract(slPips.multiply(symbolParameters.point(), MC), MC);
        BigDecimal tpPrice = price.add(tpPips.multiply(symbolParameters.point(), MC), MC);
        yield createPriceRiskLevel(garchForecast.timestamp(), OrderType.ORDER_TYPE_BUY, tpPrice, slPrice);
      }
      case ORDER_TYPE_SELL -> {
        BigDecimal slPrice = price.add(slPips.multiply(symbolParameters.point(), MC), MC);
        BigDecimal tpPrice = price.subtract(tpPips.multiply(symbolParameters.point(), MC), MC);
        yield createPriceRiskLevel(garchForecast.timestamp(), OrderType.ORDER_TYPE_SELL, tpPrice, slPrice);
      }
    };
  }

  @Contract(pure = true)
  public static Function<GarchForecast, PriceRiskLevel> getPriceRiskLevelByGarchForecast(PriceRiskLevelInput priceRiskLevelInput, SymbolParameters symbolParameters) {
    return garchForecast -> {
      log.info("getPriceRiskLevelByGarchForecast(): [priceRiskLevelInput={}, garchForecastDto={}]", priceRiskLevelInput, garchForecast);
      PriceRiskLevel priceRiskLevel = PriceRiskLevelUtils.fromSigmaAgg(garchForecast.price(), garchForecast, symbolParameters, priceRiskLevelInput);
      return new PriceRiskLevel() {
        @Override
        public OrderType orderType() {
          return garchForecast.orderType();
        }

        @Override
        public BigDecimal slPrice() {
          return priceRiskLevel.slPrice();
        }

        @Override
        public OffsetDateTime timestamp() {
          return garchForecast.timestamp();
        }

        @Override
        public BigDecimal tpPrice() {
          return priceRiskLevel.tpPrice();
        }
      };
    };
  }
}

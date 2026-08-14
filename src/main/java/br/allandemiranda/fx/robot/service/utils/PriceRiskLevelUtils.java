package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.analysis.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.analysis.PriceRiskLevelDto;
import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.input.PriceRiskLevelInputDto;
import br.allandemiranda.fx.robot.enums.PositionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public final class PriceRiskLevelUtils {

  private static final MathContext MC = MathContext.DECIMAL64;

  private static PriceRiskLevelUtils.PriceRiskLevel fromSigmaAgg(BigDecimal price, BigDecimal pipSize, BigDecimal sigmaAgg, PositionType positionType, BigDecimal kSL, BigDecimal kTP) {
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
        yield new PriceRiskLevelUtils.PriceRiskLevel(tpPrice, slPrice);
      }
      case POSITION_TYPE_SELL -> {
        BigDecimal slPrice = price.add(slPips.multiply(pipSize, MC), MC);
        BigDecimal tpPrice = price.subtract(tpPips.multiply(pipSize, MC), MC);
        yield new PriceRiskLevelUtils.PriceRiskLevel(tpPrice, slPrice);
      }
    };
  }

  public static Function<GarchForecastDto, PriceRiskLevelDto> getPriceRiskLevelByGarchForecast(PriceRiskLevelInputDto priceRiskLevelInputDto, SymbolDto symbolDto) {
    return garchForecastDto -> {
      PriceRiskLevelUtils.log.info("getPriceRiskLevelByGarchForecast(): [priceRiskLevelInputDto={}, garchForecastDto={}]", priceRiskLevelInputDto, garchForecastDto);
      PriceRiskLevel priceRiskLevel = PriceRiskLevelUtils.fromSigmaAgg(garchForecastDto.price(), symbolDto.point(), BigDecimal.valueOf(garchForecastDto.sigmaAgg()), garchForecastDto.positionType(), priceRiskLevelInputDto.kSL(),
          priceRiskLevelInputDto.kTP());
      return new PriceRiskLevelDto(garchForecastDto.timestamp(), garchForecastDto.positionType(), priceRiskLevel.tpPrice(), priceRiskLevel.slPrice());
    };
  }

  private record PriceRiskLevel(@NotNull @Positive BigDecimal tpPrice, @NotNull @Positive BigDecimal slPrice) {

  }
}

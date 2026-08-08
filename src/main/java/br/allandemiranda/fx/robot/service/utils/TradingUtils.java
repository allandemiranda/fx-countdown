package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.analysis.PriceRiskLevelDto;
import br.allandemiranda.fx.robot.dto.analysis.TradingDto;
import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.dto.core.TickDto;
import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.enums.PositionType;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@UtilityClass
public class TradingUtils {

  private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
  private static final MathContext MC = MathContext.DECIMAL64;

  private static DealReason getDealReason(TickDto tickDto, BigDecimal tp, BigDecimal sl, PositionType type) {
    return switch (type) {
      case POSITION_TYPE_BUY -> TradingUtils.getDealReason(tickDto.bid(), tp, sl, type);
      case POSITION_TYPE_SELL -> TradingUtils.getDealReason(tickDto.ask(), tp, sl, type);
    };
  }

  private static DealReason getDealReason(BigDecimal price, BigDecimal tp, BigDecimal sl, PositionType type) {
    return switch (type) {
      case POSITION_TYPE_BUY -> {
        if (price.compareTo(sl) <= 0) {
          yield DealReason.DEAL_REASON_SL;
        } else if (price.compareTo(tp) >= 0) {
          yield DealReason.DEAL_REASON_TP;
        } else {
          yield null;
        }
      }
      case POSITION_TYPE_SELL -> {
        if (price.compareTo(sl) >= 0) {
          yield DealReason.DEAL_REASON_SL;
        } else if (price.compareTo(tp) <= 0) {
          yield DealReason.DEAL_REASON_TP;
        } else {
          yield null;
        }
      }
    };
  }

  private static BigDecimal getRollover(OffsetDateTime openTime, OffsetDateTime closeTime, BigDecimal swapLong, BigDecimal swapShort, PositionType type) {
    if (!closeTime.isAfter(openTime)) {
      return BigDecimal.ZERO;
    }

    BigDecimal swap = switch (type) {
      case POSITION_TYPE_BUY -> swapLong;
      case POSITION_TYPE_SELL -> swapShort;
    };

    return openTime.toLocalDate()
        .plusDays(1)
        .datesUntil(closeTime.toLocalDate().plusDays(1))
        .map(date -> DayOfWeek.WEDNESDAY.equals(date.getDayOfWeek()) ? swap.multiply(BigDecimal.valueOf(3)) : swap)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public static BigDecimal getPoints(TickDto open, TickDto close, PositionType type, BigDecimal symbolPoint) {
    BigDecimal priceDiff = switch (type) {
      case POSITION_TYPE_BUY -> open.ask().subtract(close.bid());
      case POSITION_TYPE_SELL -> open.bid().subtract(close.ask());
    };
    return priceDiff.divide(symbolPoint, TradingUtils.DEFAULT_ROUNDING_MODE);
  }

  private static BigDecimal getPoints(TickDto open, BigDecimal closePrice, PositionType type, BigDecimal symbolPoint) {
    BigDecimal priceDiff = switch (type) {
      case POSITION_TYPE_BUY -> open.ask().subtract(closePrice);
      case POSITION_TYPE_SELL -> open.bid().subtract(closePrice);
    };
    return priceDiff.divide(symbolPoint, TradingUtils.DEFAULT_ROUNDING_MODE);
  }

  public Function<List<TickDto>, Mono<TradingDto>> getTradingResult(PriceRiskLevelDto priceRiskLevelDto, SymbolDto symbolDto) {
    return ticks -> {
      TradingDto tradingDto = new TradingDto(priceRiskLevelDto.timestamp(), priceRiskLevelDto.positionType(), ticks.getFirst(), ticks.getFirst(), null);
      BigDecimal profitExpected = TradingUtils.getPoints(tradingDto.openTick(), priceRiskLevelDto.tpPrice(), priceRiskLevelDto.positionType(), symbolDto.point());
      for (TickDto tick : ticks) {
        tradingDto = tradingDto.toBuilder().closeTick(tick).build();
        DealReason dealReason = TradingUtils.getDealReason(tick, priceRiskLevelDto.tpPrice(), priceRiskLevelDto.slPrice(), priceRiskLevelDto.positionType());
        if (dealReason == null) {
          BigDecimal rollover = TradingUtils.getRollover(tradingDto.openTick().timestamp(), tradingDto.closeTick().timestamp(), symbolDto.swapLong(), symbolDto.swapShort(), priceRiskLevelDto.positionType());
          if (profitExpected.add(rollover, MC).compareTo(BigDecimal.ZERO) <= 0) {
            tradingDto = tradingDto.toBuilder().dealReason(DealReason.DEAL_REASON_ROLLOVER).build();
            break;
          }
        } else {
          tradingDto = tradingDto.toBuilder().dealReason(dealReason).build();
          break;
        }
      }
      return Mono.just(tradingDto);
    };
  }
}

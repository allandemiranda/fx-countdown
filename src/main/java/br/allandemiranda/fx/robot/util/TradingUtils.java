package br.allandemiranda.fx.robot.util;

import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.enums.PositionType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TradingUtils {

  public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

  public static BigDecimal getSpread(BigDecimal ask, BigDecimal bid, BigDecimal point) {
    return ask.subtract(bid).divide(point, point.scale(), TradingUtils.DEFAULT_ROUNDING_MODE);
  }

  public static DealReason getDealReason(BigDecimal ask, BigDecimal bid, BigDecimal tp, BigDecimal sl, PositionType type) {
    return switch (type) {
      case POSITION_TYPE_BUY -> TradingUtils.getDealReason(bid, tp, sl, type);
      case POSITION_TYPE_SELL -> TradingUtils.getDealReason(ask, tp, sl, type);
    };
  }

  public static DealReason getDealReason(BigDecimal price, BigDecimal tp, BigDecimal sl, PositionType type) {
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

  public static BigDecimal getRollover(OffsetDateTime openTime, OffsetDateTime closeTime, BigDecimal swapLong, BigDecimal swapShort, PositionType type) {
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

  public static BigDecimal getPoints(BigDecimal openPrice, BigDecimal closePrice, BigDecimal point, PositionType type) {
    BigDecimal priceDiff = switch (type) {
      case POSITION_TYPE_BUY -> closePrice.subtract(openPrice);
      case POSITION_TYPE_SELL -> openPrice.subtract(closePrice);
    };
    return priceDiff.divide(point, point.scale(), TradingUtils.DEFAULT_ROUNDING_MODE);
  }

  public static BigDecimal getPoints(BigDecimal price, BigDecimal point) {
    return price.divide(point, point.scale(), TradingUtils.DEFAULT_ROUNDING_MODE);
  }
}

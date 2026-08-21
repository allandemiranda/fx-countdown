package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.provider.PriceRiskLevel;
import br.allandemiranda.fx.robot.dto.provider.Trading;
import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.enums.OrderType;
import br.allandemiranda.fx.robot.enums.PositionType;
import br.allandemiranda.fx.robot.model.core.SymbolParameters;
import br.allandemiranda.fx.robot.model.core.Tick;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
@Slf4j
@UtilityClass
public class TradingUtils {

  private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
  private static final ZoneId FOREX_ROLLOVER_ZONE = ZoneId.of("America/New_York");
  private static final MathContext MC = MathContext.DECIMAL64;
  private static final int POINTS_SCALE = 2;
  private static final LocalTime ROLLOVER_CUTOFF_TIME = LocalTime.of(17, 0);


  @Contract(pure = true)
  private static BigDecimal getClosePrice(Tick tick, PositionType positionType) {
    return switch (positionType) {
      case POSITION_TYPE_BUY -> tick.bid();
      case POSITION_TYPE_SELL -> tick.ask();
    };
  }


  @Contract(pure = true)
  private static @Nullable DealReason getDealReason(Tick tick, BigDecimal tp, BigDecimal sl, PositionType type) {
    BigDecimal price = TradingUtils.getClosePrice(tick, type);

    return switch (type) {
      case POSITION_TYPE_BUY -> {
        if (price.compareTo(sl) <= 0) {
          yield DealReason.DEAL_REASON_SL;
        }
        if (price.compareTo(tp) >= 0) {
          yield DealReason.DEAL_REASON_TP;
        }
        yield null;
      }
      case POSITION_TYPE_SELL -> {
        if (price.compareTo(sl) >= 0) {
          yield DealReason.DEAL_REASON_SL;
        }
        if (price.compareTo(tp) <= 0) {
          yield DealReason.DEAL_REASON_TP;
        }
        yield null;
      }
    };
  }

  @Contract(pure = true)
  private static BigDecimal getExpectedProfitPoints(BigDecimal openPice, BigDecimal tpPrice, PositionType type, BigDecimal symbolPoint) {
    BigDecimal priceDiff = switch (type) {
      case POSITION_TYPE_BUY -> tpPrice.subtract(openPice);
      case POSITION_TYPE_SELL -> openPice.subtract(tpPrice);
    };

    return priceDiff.divide(symbolPoint, POINTS_SCALE, DEFAULT_ROUNDING_MODE);
  }

  @Contract(pure = true)
  private static ZonedDateTime getFirstCutoffAfter(ZonedDateTime openZoned) {
    ZonedDateTime cutoff = openZoned.toLocalDate().atTime(ROLLOVER_CUTOFF_TIME).atZone(FOREX_ROLLOVER_ZONE);
    return cutoff.isAfter(openZoned) ? cutoff : cutoff.plusDays(1);
  }

  @Contract(pure = true)
  private static BigDecimal getOpenPrice(Tick tick, PositionType positionType) {
    return switch (positionType) {
      case POSITION_TYPE_BUY -> tick.ask();
      case POSITION_TYPE_SELL -> tick.bid();
    };
  }

  @Contract(pure = true)
  private static BigDecimal getPoints(Tick open, Tick close, PositionType type, BigDecimal symbolPoint) {
    BigDecimal openPrice = TradingUtils.getOpenPrice(open, type);
    BigDecimal closePrice = TradingUtils.getClosePrice(close, type);

    BigDecimal priceDiff = switch (type) {
      case POSITION_TYPE_BUY -> closePrice.subtract(openPrice);
      case POSITION_TYPE_SELL -> openPrice.subtract(closePrice);
    };
    return priceDiff.divide(symbolPoint, POINTS_SCALE, DEFAULT_ROUNDING_MODE);
  }

  @Contract(pure = true)
  private static BigDecimal getRolloverMaxCost(Tick openTick, BigDecimal tpPrice, SymbolParameters symbolParameters, PositionType positionType) {
    BigDecimal swapRate = TradingUtils.getSwapRate(symbolParameters, positionType);

    if (swapRate.compareTo(BigDecimal.ZERO) >= 0) {
      return BigDecimal.ZERO;
    }

    BigDecimal openPrice = TradingUtils.getOpenPrice(openTick, positionType);
    BigDecimal remainingProfit = TradingUtils.getExpectedProfitPoints(openPrice, tpPrice, positionType, symbolParameters.point());

    ZonedDateTime openZoned = openTick.timestamp().atZoneSameInstant(FOREX_ROLLOVER_ZONE);
    ZonedDateTime currentCutoff = getFirstCutoffAfter(openZoned);

    BigDecimal totalCost = BigDecimal.ZERO;

    while (remainingProfit.compareTo(BigDecimal.ZERO) > 0) {
      DayOfWeek day = currentCutoff.getDayOfWeek();

      if (!DayOfWeek.SATURDAY.equals(day) && !DayOfWeek.SUNDAY.equals(day)) {
        BigDecimal multiplier = DayOfWeek.WEDNESDAY.equals(day) ? BigDecimal.valueOf(3) : BigDecimal.ONE;
        BigDecimal currentSwapCost = swapRate.multiply(multiplier, MC);

        totalCost = totalCost.add(currentSwapCost, MC);
        remainingProfit = remainingProfit.add(currentSwapCost, MC);

        if (remainingProfit.compareTo(BigDecimal.ZERO) <= 0) {
          return totalCost;
        }
      }

      currentCutoff = currentCutoff.plusDays(1);
    }

    return totalCost;
  }

  @Contract(pure = true)
  private static BigDecimal getSwapRate(SymbolParameters symbolParameters, PositionType positionType) {
    return PositionType.POSITION_TYPE_BUY.equals(positionType) ? symbolParameters.swapLong() : symbolParameters.swapShort();
  }

  @Contract(pure = true)
  public static OffsetDateTime getMaxRolloverCutoff(OffsetDateTime openTime, BigDecimal openPrice, BigDecimal tpPrice, SymbolParameters symbolParameters, PositionType positionType) {
    BigDecimal swapRate = PositionType.POSITION_TYPE_BUY.equals(positionType) ? symbolParameters.swapLong() : symbolParameters.swapShort();

    if (swapRate.compareTo(BigDecimal.ZERO) >= 0) {
      return OffsetDateTime.MAX;
    }

    BigDecimal remainingProfit = TradingUtils.getExpectedProfitPoints(openPrice, tpPrice, positionType, symbolParameters.point());

    ZonedDateTime openZoned = openTime.atZoneSameInstant(FOREX_ROLLOVER_ZONE);
    ZonedDateTime currentCutoff = TradingUtils.getFirstCutoffAfter(openZoned);

    while (remainingProfit.compareTo(BigDecimal.ZERO) > 0) {
      DayOfWeek day = currentCutoff.getDayOfWeek();

      if (!DayOfWeek.SATURDAY.equals(day) && !DayOfWeek.SUNDAY.equals(day)) {
        BigDecimal multiplier = DayOfWeek.WEDNESDAY.equals(day) ? BigDecimal.valueOf(3) : BigDecimal.ONE;
        BigDecimal currentSwapCost = swapRate.multiply(multiplier, MC);

        remainingProfit = remainingProfit.add(currentSwapCost, MC);
        if (remainingProfit.compareTo(BigDecimal.ZERO) <= 0) {
          return currentCutoff.toOffsetDateTime().withOffsetSameInstant(openTime.getOffset());
        }
      }

      currentCutoff = currentCutoff.plusDays(1);
    }

    return currentCutoff.toOffsetDateTime().withOffsetSameInstant(openTime.getOffset());
  }

  public @Unmodifiable List<? extends Tick> getMaxRolloverCutoffTicks(@Unmodifiable List<? extends Tick> ticks, OffsetDateTime openDataTime, OffsetDateTime maxRolloverCutoff) {
    return ticks.stream().filter(tick -> !tick.timestamp().isAfter(maxRolloverCutoff) && !tick.timestamp().isBefore(openDataTime)).toList();
  }

  @Contract(pure = true)
  public static PositionType getPositionType(OrderType orderType) {
    return OrderType.ORDER_TYPE_BUY.equals(orderType)
        ? PositionType.POSITION_TYPE_BUY
        : PositionType.POSITION_TYPE_SELL;
  }

  @Contract(pure = true)
  public static Function<@Unmodifiable List<? extends Tick>, Trading> getTradingResult(PriceRiskLevel priceRiskLevel, SymbolParameters symbolParameters) {
    return ticks -> {
      PositionType positionType = TradingUtils.getPositionType(priceRiskLevel.orderType());
      Tick openTick = ticks.getFirst();

      Tick finalCloseTick = openTick;
      DealReason finalDealReason = null;

      for (Tick tick : ticks) {
        finalCloseTick = tick;
        DealReason reason = TradingUtils.getDealReason(tick, priceRiskLevel.tpPrice(), priceRiskLevel.slPrice(), positionType);

        if (Objects.nonNull(reason)) {
          finalDealReason = reason;
          break;
        }
      }

      if (Objects.isNull(finalDealReason)) {
        BigDecimal cost = TradingUtils.getRolloverMaxCost(openTick, priceRiskLevel.tpPrice(), symbolParameters, positionType);
        BigDecimal profit = TradingUtils.getPoints(openTick, finalCloseTick, positionType, symbolParameters.point());

        if (cost.add(profit, MC).compareTo(BigDecimal.ZERO) <= 0) {
          finalDealReason = DealReason.DEAL_REASON_ROLLOVER;
        }
      }

      DealReason dealReason = finalDealReason;
      return new Trading() {
        @Override
        public DealReason dealReason() {
          return dealReason;
        }

        @Override
        public PositionType positionType() {
          return positionType;
        }

        @Override
        public OffsetDateTime timestamp() {
          return priceRiskLevel.timestamp();
        }
      };
    };
  }
}
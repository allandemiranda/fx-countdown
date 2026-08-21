package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.provider.Candlestick;
import br.allandemiranda.fx.robot.enums.OrderType;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.core.Tick;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Slf4j
@UtilityClass
public class CandlestickUtils {

  @Contract(pure = true)
  private static BigDecimal getPrice(Tick tick, OrderType orderType) {
    return switch (orderType) {
      case ORDER_TYPE_BUY -> tick.ask();
      case ORDER_TYPE_SELL -> tick.bid();
    };
  }

  @Contract(pure = true)
  private static Candlestick toCandlestick(OffsetDateTime timestamp, List<? extends Tick> bucket, OrderType orderType) {
    BigDecimal open = CandlestickUtils.getPrice(bucket.getFirst(), orderType);
    BigDecimal close = CandlestickUtils.getPrice(bucket.getLast(), orderType);

    BigDecimal high = open;
    BigDecimal low = open;

    for (Tick tick : bucket) {
      BigDecimal price = CandlestickUtils.getPrice(tick, orderType);
      if (price.compareTo(high) > 0) {
        high = price;
      }
      if (price.compareTo(low) < 0) {
        low = price;
      }
    }

    final BigDecimal finalHigh = high;
    final BigDecimal finalLow = low;

    return new Candlestick() {
      @Override
      public BigDecimal close() {
        return close;
      }

      @Override
      public BigDecimal high() {
        return finalHigh;
      }

      @Override
      public BigDecimal low() {
        return finalLow;
      }

      @Override
      public BigDecimal open() {
        return open;
      }

      @Override
      public OffsetDateTime timestamp() {
        return timestamp;
      }
    };
  }

  @Contract(pure = true)
  public static @Unmodifiable List<Candlestick> getCandlesticksByTicks(@Unmodifiable List<? extends Tick> ticks, Timeframe timeframe, OrderType orderType) {
    return ticks.stream()
        .collect(Collectors.groupingBy(tick -> timeframe.truncateTimestamp(tick.timestamp()), LinkedHashMap::new, Collectors.toList()))
        .entrySet()
        .parallelStream()
        .map(entry -> toCandlestick(entry.getKey(), entry.getValue(), orderType))
        .sorted(Comparator.comparing(Timeseries::timestamp))
        .toList();
  }

  @Contract(pure = true)
  public static @Unmodifiable List<Candlestick> getCandlesticksByTicks(@Unmodifiable List<? extends Tick> ticks, Timeframe timeframe, OrderType orderType, OffsetDateTime timestamp,
      @Range(from = 1, to = Integer.MAX_VALUE) int horizon) {
    OffsetDateTime targetBucket = timeframe.truncateTimestamp(timestamp);

    Map<OffsetDateTime, List<Tick>> buckets = ticks.stream().filter(tick -> !timeframe.truncateTimestamp(tick.timestamp()).isAfter(targetBucket))
        .collect(Collectors.groupingBy(tick -> timeframe.truncateTimestamp(tick.timestamp()), LinkedHashMap::new, Collectors.toList()));

    if (!buckets.containsKey(targetBucket)) {
      return List.of();
    }

    List<Map.Entry<OffsetDateTime, List<Tick>>> entryList = new ArrayList<>(buckets.entrySet());
    int targetIndex = entryList.size() - 1;

    int startIndex = targetIndex - horizon;
    if (startIndex < 0) {
      return List.of();
    }

    return entryList.subList(startIndex, targetIndex + 1).parallelStream().map(entry -> toCandlestick(entry.getKey(), entry.getValue(), orderType)).sorted(Comparator.comparing(Timeseries::timestamp)).toList();
  }
}
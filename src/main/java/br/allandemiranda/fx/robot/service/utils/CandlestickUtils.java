package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.Timeseries;
import br.allandemiranda.fx.robot.dto.core.Candlestick;
import br.allandemiranda.fx.robot.dto.core.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.core.CandlestickDto;
import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.dto.core.Tick;
import br.allandemiranda.fx.robot.dto.core.TickCreateDto;
import br.allandemiranda.fx.robot.dto.core.TickDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class CandlestickUtils {

  public static <T extends Tick, C extends Candlestick> List<C> getCandlesticksAsk(List<T> tickDtos, Timeframe timeframe) {
    if (tickDtos == null || tickDtos.isEmpty() || timeframe == null) {
      return List.of();
    }

    Map<OffsetDateTime, List<T>> groupedByBucket = tickDtos.stream()
        .sorted(Comparator.comparing(Timeseries::timestamp))
        .collect(Collectors.groupingBy(tick -> timeframe.truncateTimestamp(tick.timestamp()), Collectors.toList()));

    List<C> candlesticks = new ArrayList<>();

    groupedByBucket.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(entry -> {
          OffsetDateTime bucketTimestamp = entry.getKey();
          List<T> ticksInBucket = entry.getValue();

          BigDecimal open = ticksInBucket.getFirst().ask();
          BigDecimal close = ticksInBucket.getLast().ask();

          BigDecimal high = open;
          BigDecimal low = open;

          for (T tick : ticksInBucket) {
            BigDecimal currentAsk = tick.ask();
            if (currentAsk.compareTo(high) > 0) {
              high = currentAsk;
            }
            if (currentAsk.compareTo(low) < 0) {
              low = currentAsk;
            }
          }

          C candlestick = switch (tickDtos.getFirst()) {
            case TickDto tickDto -> {
              SymbolDto symbolDto = tickDto.symbolDto();

              yield (C) new CandlestickDto(
                  UUID.randomUUID(),
                  symbolDto,
                  timeframe,
                  bucketTimestamp,
                  open,
                  high,
                  low,
                  close
              );
            }

            case TickCreateDto tickCreateDto -> (C) new CandlestickCreateDto(
                bucketTimestamp,
                open,
                high,
                low,
                close
            );

            default -> throw new IllegalArgumentException(
                "Unsupported Tick type: " + tickDtos.getFirst().getClass()
            );
          };

          candlesticks.add(candlestick);
        });

    return candlesticks;
  }

}

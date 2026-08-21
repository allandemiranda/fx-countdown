package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.model.provider.Timeseries;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Slf4j
@UtilityClass
public class TimeseriesUtils {

  @Contract(pure = true)
  public static <T extends Timeseries> @Unmodifiable List<T> getTimeseriesWindow(@Unmodifiable List<T> series, OffsetDateTime timestamp, @Range(from = 1, to = Integer.MAX_VALUE) int horizon) {

    if (horizon + 1 > series.size()) {
      return List.of();
    }

    int targetIndex = Collections.binarySearch(series, null, (item, _) -> item == null ? 0 : item.timestamp().compareTo(timestamp));

    if (targetIndex < 0) {
      return List.of();
    }

    int startIndex = targetIndex - horizon;

    if (startIndex < 0) {
      return List.of();
    }

    return Collections.unmodifiableList(series.subList(startIndex, targetIndex + 1));
  }
}
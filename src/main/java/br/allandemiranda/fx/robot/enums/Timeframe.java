package br.allandemiranda.fx.robot.enums;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;

@Getter
@RequiredArgsConstructor
@Description("ENUM_TIMEFRAMES")
public enum Timeframe {
  PERIOD_M1("M1", Duration.ofMinutes(1)),
  PERIOD_M2("M2", Duration.ofMinutes(2)),
  PERIOD_M3("M3", Duration.ofMinutes(3)),
  PERIOD_M4("M4", Duration.ofMinutes(4)),
  PERIOD_M5("M5", Duration.ofMinutes(5)),
  PERIOD_M6("M6", Duration.ofMinutes(6)),
  PERIOD_M10("M10", Duration.ofMinutes(10)),
  PERIOD_M12("M12", Duration.ofMinutes(12)),
  PERIOD_M15("M15", Duration.ofMinutes(15)),
  PERIOD_M20("M20", Duration.ofMinutes(20)),
  PERIOD_M30("M30", Duration.ofMinutes(30)),
  PERIOD_H1("H1", Duration.ofHours(1)),
  PERIOD_H2("H2", Duration.ofHours(2)),
  PERIOD_H3("H3", Duration.ofHours(3)),
  PERIOD_H4("H4", Duration.ofHours(4)),
  PERIOD_H6("H6", Duration.ofHours(6)),
  PERIOD_H8("H8", Duration.ofHours(8)),
  PERIOD_H12("H12", Duration.ofHours(12)),
  PERIOD_D1("D1", Duration.ofDays(1)),
  PERIOD_W1("W1", Duration.ofDays(7)),
  PERIOD_MN1("MN1", Duration.ofDays(30));

  @NonNull
  private final String code;

  @NonNull
  private final Duration duration;

  public OffsetDateTime truncateTimestamp(OffsetDateTime timestamp) {
    long durationSeconds = duration.getSeconds();

    if (this == PERIOD_D1) {
      return timestamp.truncatedTo(ChronoUnit.DAYS);
    }
    if (this == PERIOD_W1) {
      return timestamp.truncatedTo(ChronoUnit.DAYS).minusDays(timestamp.getDayOfWeek().getValue() - 1L);
    }
    if (this == PERIOD_MN1) {
      return timestamp.withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS);
    }

    long epochSecond = timestamp.toEpochSecond();
    long remainder = epochSecond % durationSeconds;

    return timestamp.minusSeconds(remainder).truncatedTo(ChronoUnit.SECONDS);
  }
}
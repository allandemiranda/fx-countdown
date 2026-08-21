package br.allandemiranda.fx.robot.enums;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * Supported chart timeframe intervals corresponding to MetaTrader 5 standard periods (ENUM_TIMEFRAMES).
 */
@NullMarked
@Getter
@AllArgsConstructor
public enum Timeframe {

  /**
   * 1 Minute timeframe.
   */
  PERIOD_M1("M1", Duration.ofMinutes(1)),

  /**
   * 2 Minutes timeframe.
   */
  PERIOD_M2("M2", Duration.ofMinutes(2)),

  /**
   * 3 Minutes timeframe.
   */
  PERIOD_M3("M3", Duration.ofMinutes(3)),

  /**
   * 4 Minutes timeframe.
   */
  PERIOD_M4("M4", Duration.ofMinutes(4)),

  /**
   * 5 Minutes timeframe.
   */
  PERIOD_M5("M5", Duration.ofMinutes(5)),

  /**
   * 6 Minutes timeframe.
   */
  PERIOD_M6("M6", Duration.ofMinutes(6)),

  /**
   * 10 Minutes timeframe.
   */
  PERIOD_M10("M10", Duration.ofMinutes(10)),

  /**
   * 12 Minutes timeframe.
   */
  PERIOD_M12("M12", Duration.ofMinutes(12)),

  /**
   * 15 Minutes timeframe.
   */
  PERIOD_M15("M15", Duration.ofMinutes(15)),

  /**
   * 20 Minutes timeframe.
   */
  PERIOD_M20("M20", Duration.ofMinutes(20)),

  /**
   * 30 Minutes timeframe.
   */
  PERIOD_M30("M30", Duration.ofMinutes(30)),

  /**
   * 1 Hour timeframe.
   */
  PERIOD_H1("H1", Duration.ofHours(1)),

  /**
   * 2 Hours timeframe.
   */
  PERIOD_H2("H2", Duration.ofHours(2)),

  /**
   * 3 Hours timeframe.
   */
  PERIOD_H3("H3", Duration.ofHours(3)),

  /**
   * 4 Hours timeframe.
   */
  PERIOD_H4("H4", Duration.ofHours(4)),

  /**
   * 6 Hours timeframe.
   */
  PERIOD_H6("H6", Duration.ofHours(6)),

  /**
   * 8 Hours timeframe.
   */
  PERIOD_H8("H8", Duration.ofHours(8)),

  /**
   * 12 Hours timeframe.
   */
  PERIOD_H12("H12", Duration.ofHours(12)),

  /**
   * 1 Day (Daily) timeframe.
   */
  PERIOD_D1("D1", Duration.ofDays(1)),

  /**
   * 1 Week (Weekly) timeframe.
   */
  PERIOD_W1("W1", Duration.ofDays(7)),

  /**
   * 1 Month (Monthly) timeframe.
   */
  PERIOD_MN1("MN1", Duration.ofDays(30));

  /**
   * Short code used in file names and paths (e.g. M1, H1).
   */
  private final String code;

  /**
   * Temporal duration of a single candlesticks in this timeframe.
   */
  private final Duration duration;

  /**
   * Truncates a given timestamp to the start boundary bucket of this timeframe interval.
   *
   * @param timestamp the observation timestamp to truncate
   * @return the truncated start timestamp for the corresponding candlesticks bar
   */
  @Contract(pure = true)
  public OffsetDateTime truncateTimestamp(OffsetDateTime timestamp) {
    long durationSeconds = this.duration.getSeconds();

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

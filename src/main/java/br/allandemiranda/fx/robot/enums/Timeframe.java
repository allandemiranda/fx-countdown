package br.allandemiranda.fx.robot.enums;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;

/**
 * Chart Timeframes
 */
@Getter
@RequiredArgsConstructor
@Description("ENUM_TIMEFRAMES")
public enum Timeframe {
  PERIOD_M1("M1"),
  PERIOD_M2("M2"),
  PERIOD_M3("M23"),
  PERIOD_M4("M4"),
  PERIOD_M5("M5"),
  PERIOD_M6("M6"),
  PERIOD_M10("M10"),
  PERIOD_M12("M12"),
  PERIOD_M15("M15"),
  PERIOD_M20("M20"),
  PERIOD_M30("M30"),
  PERIOD_H1("H1"),
  PERIOD_H2("H2"),
  PERIOD_H3("H3"),
  PERIOD_H4("H4"),
  PERIOD_H6("H6"),
  PERIOD_H8("H8"),
  PERIOD_H12("H12"),
  PERIOD_D1("D1"),
  PERIOD_W1("W1"),
  PERIOD_MN1("NM1");

  @NotNull
  private final String textValue;
}

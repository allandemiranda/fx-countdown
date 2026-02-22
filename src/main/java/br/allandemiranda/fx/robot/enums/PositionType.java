package br.allandemiranda.fx.robot.enums;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;

/**
 * Direction of an open position (buy or sell) is defined by the value from the ENUM_POSITION_TYPE enumeration.
 * In order to obtain the type of open position use the PositionGetInteger() function with the POSITION_TYPE modifier.
 */
@Getter
@RequiredArgsConstructor
@Description("ENUM_POSITION_TYPE")
public enum PositionType {
  POSITION_TYPE_BUY("Buy"),
  POSITION_TYPE_SELL("Sell");

  @NotNull
  private final String textValue;
}

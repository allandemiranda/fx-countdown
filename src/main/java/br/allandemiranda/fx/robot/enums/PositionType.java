package br.allandemiranda.fx.robot.enums;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;

@Getter
@RequiredArgsConstructor
@Description("ENUM_POSITION_TYPE")
public enum PositionType {
  POSITION_TYPE_BUY("Buy"),
  POSITION_TYPE_SELL("Sell");

  @NotNull
  private final String textValue;
}

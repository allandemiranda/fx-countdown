package br.allandemiranda.fx.robot.enums;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MLLabel {
  NEUTRAL(0f), BUY(1f), SELL(2f);

  @NotNull
  private final float value;

}

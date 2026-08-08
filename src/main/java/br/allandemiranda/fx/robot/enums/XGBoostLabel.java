package br.allandemiranda.fx.robot.enums;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum XGBoostLabel {
  NOT_OPEN(0), OPEN(1);

  @NotNull
  private final int value;
}

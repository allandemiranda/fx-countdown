package br.allandemiranda.fx.robot.enums;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Getter
@AllArgsConstructor
public enum XgBoostWatch {
  TRAIN("train"),
  VALIDATION("validation"),
  NEW_DATA("new_data");

  @NotNull
  private final String value;
}

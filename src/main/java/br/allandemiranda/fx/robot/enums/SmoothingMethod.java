package br.allandemiranda.fx.robot.enums;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;

/**
 * Smoothing Methods
 */
@Getter
@RequiredArgsConstructor
@Description("ENUM_MA_METHOD")
public enum SmoothingMethod {
  MODE_SMA("Simple"),
  MODE_EMA("Exponential"),
  MODE_SMMA("Smmothed"),
  MODE_LWMA("Linear Weighted");

  @NotNull
  private final String textValue;
}

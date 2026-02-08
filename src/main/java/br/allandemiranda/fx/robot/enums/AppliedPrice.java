package br.allandemiranda.fx.robot.enums;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;

/**
 * Price Constants
 */
@Getter
@RequiredArgsConstructor
@Description("ENUM_APPLIED_PRICE")
public enum AppliedPrice {
  PRICE_CLOSE("Close"),
  PRICE_OPEN("Open"),
  PRICE_HIGH("High"),
  PRICE_LOW("Low"),
  PRICE_MEDIAN("Median Price (HL/2)"),
  PRICE_TYPICAL("Typical Price (HLC/2)"),
  PRICE_WEIGHTED("Weighted Close (HLCC/4)");

  @NotNull
  private final String textValue;
}

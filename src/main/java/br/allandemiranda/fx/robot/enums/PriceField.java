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
@Description("ENUM_STO_PRICE")
public enum PriceField {
  STO_LOWHIGH("Low/High"),
  STO_CLOSECLOSE("Close/Close");

  @NotNull
  private final String textValue;
}

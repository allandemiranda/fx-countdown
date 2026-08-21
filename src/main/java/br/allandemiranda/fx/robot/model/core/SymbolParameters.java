package br.allandemiranda.fx.robot.model.core;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public interface SymbolParameters {

  @NotNull
  @Positive
  @DecimalMax("1,0")
  BigDecimal point();

  @NotNull
  BigDecimal swapLong();

  @NotNull
  BigDecimal swapShort();
}

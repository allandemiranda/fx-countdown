package br.allandemiranda.fx.robot.model.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public interface PriceRiskLevelInput {

  @NotNull
  @Positive
  BigDecimal kSL();

  @NotNull
  @Positive
  BigDecimal kTP();
}

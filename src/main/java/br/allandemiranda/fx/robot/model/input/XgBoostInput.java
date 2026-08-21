package br.allandemiranda.fx.robot.model.input;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public interface XgBoostInput {

  @Positive
  int horizon();

  @Positive
  @DecimalMax("1.0")
  BigDecimal minimalLevelAccepted();

  @Positive
  @DecimalMax("1.0")
  BigDecimal validationPercentage();
}

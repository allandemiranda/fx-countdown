package br.allandemiranda.fx.robot.model.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public interface GarchInput {

  @Positive
  int horizon();

  @Min(50)
  int priceSize();
}

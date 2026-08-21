package br.allandemiranda.fx.robot.model.input;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface IStochastic {

  @Positive
  short dPeriod();

  @Positive
  short kPeriod();

  @NotNull
  SmoothingMethod maMethod();

  @NotNull
  PriceField priceField();

  @Positive
  short slowing();
}

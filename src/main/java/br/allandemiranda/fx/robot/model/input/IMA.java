package br.allandemiranda.fx.robot.model.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface IMA {

  @NotNull
  AppliedPrice appliedPrice();

  @NotNull
  SmoothingMethod maMethod();

  @Positive
  short maPeriod();

  short maShift();
}

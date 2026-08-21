package br.allandemiranda.fx.robot.model.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface IRSI {

  @NotNull
  AppliedPrice appliedPrice();

  @Positive
  short maPeriod();
}

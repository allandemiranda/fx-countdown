package br.allandemiranda.fx.robot.model.input;

import br.allandemiranda.fx.robot.annotation.model.IMACDValidate;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@IMACDValidate
public interface IMACD {

  @NotNull
  AppliedPrice appliedPrice();

  @Positive
  short fastEmaPeriod();

  @Positive
  short signalPeriod();

  @Positive
  short slowEmaPeriod();
}

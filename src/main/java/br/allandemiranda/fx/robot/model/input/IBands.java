package br.allandemiranda.fx.robot.model.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public interface IBands {

  @NotNull
  AppliedPrice appliedPrice();

  @Positive
  short bandsPeriod();

  short bandsShift();

  @NotNull
  @Positive
  BigDecimal deviation();
}

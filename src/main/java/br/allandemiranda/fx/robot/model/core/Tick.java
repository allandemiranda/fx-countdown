package br.allandemiranda.fx.robot.model.core;

import br.allandemiranda.fx.robot.annotation.model.TickValidate;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@TickValidate
public interface Tick extends Timeseries {

  @NotNull
  @Positive
  BigDecimal ask();

  @NotNull
  @Positive
  BigDecimal bid();
}

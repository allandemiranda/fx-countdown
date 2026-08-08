package br.allandemiranda.fx.robot.dto.core;

import br.allandemiranda.fx.robot.dto.Timeseries;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public interface Tick extends Timeseries {

  @NotNull
  @Positive
  BigDecimal ask();

  @NotNull
  @Positive
  BigDecimal bid();
}

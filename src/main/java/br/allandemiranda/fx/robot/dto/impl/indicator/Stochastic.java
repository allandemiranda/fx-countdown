package br.allandemiranda.fx.robot.dto.impl.indicator;

import br.allandemiranda.fx.robot.dto.Timeseries;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface Stochastic extends Timeseries {

  @NotNull
  @Min(0)
  @Max(100)
  BigDecimal mainLine();

  @NotNull
  @Min(0)
  @Max(100)
  BigDecimal signalLine();
}

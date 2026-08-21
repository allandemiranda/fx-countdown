package br.allandemiranda.fx.robot.model.indicator;

import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface ATR extends Timeseries {

  @NotNull
  @DecimalMin("0.0")
  BigDecimal atr();
}

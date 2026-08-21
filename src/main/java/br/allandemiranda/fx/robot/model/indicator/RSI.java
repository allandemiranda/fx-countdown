package br.allandemiranda.fx.robot.model.indicator;

import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface RSI extends Timeseries {

  @NotNull
  @DecimalMin("0.0")
  @DecimalMax("100.0")
  BigDecimal rsi();
}

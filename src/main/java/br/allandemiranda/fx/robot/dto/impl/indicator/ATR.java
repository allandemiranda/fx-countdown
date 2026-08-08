package br.allandemiranda.fx.robot.dto.impl.indicator;

import br.allandemiranda.fx.robot.dto.Timeseries;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface ATR extends Timeseries {

  @NotNull
  BigDecimal atr();
}

package br.allandemiranda.fx.robot.model.indicator;

import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface MACD extends Timeseries {

  @NotNull
  BigDecimal mainLine();

  @NotNull
  BigDecimal signalLine();
}

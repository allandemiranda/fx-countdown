package br.allandemiranda.fx.robot.model.indicator.provider;

import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public interface Ma extends Timeseries {

  @NotNull
  @Positive
  BigDecimal ma();
}

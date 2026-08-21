package br.allandemiranda.fx.robot.dto.provider;

import br.allandemiranda.fx.robot.enums.OrderType;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public interface GarchForecast extends Timeseries, Garch {

  @NotNull
  OrderType orderType();

  @NotNull
  @Positive
  BigDecimal price();
}

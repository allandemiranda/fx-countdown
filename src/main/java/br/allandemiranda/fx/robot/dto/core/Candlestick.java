package br.allandemiranda.fx.robot.dto.core;

import br.allandemiranda.fx.robot.dto.Timeseries;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public interface Candlestick extends Timeseries {

  @NotNull
  @Positive
  BigDecimal open();

  @NotNull
  @Positive
  BigDecimal high();

  @NotNull
  @Positive
  BigDecimal low();

  @NotNull
  @Positive
  BigDecimal close();
}

package br.allandemiranda.fx.robot.dto.provider;

import br.allandemiranda.fx.robot.annotation.model.CandlestickValidate;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@CandlestickValidate
public interface Candlestick extends Timeseries {

  @NotNull
  @Positive
  BigDecimal close();

  @NotNull
  @Positive
  BigDecimal high();

  @NotNull
  @Positive
  BigDecimal low();

  @NotNull
  @Positive
  BigDecimal open();
}

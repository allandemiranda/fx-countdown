package br.allandemiranda.fx.robot.model.indicator;

import br.allandemiranda.fx.robot.annotation.model.BandsValidate;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@BandsValidate
public interface Bands extends Timeseries {

  @NotNull
  @Positive
  BigDecimal baseLine();

  @NotNull
  @Positive
  BigDecimal lowerBand();

  @NotNull
  @Positive
  BigDecimal upperBand();
}

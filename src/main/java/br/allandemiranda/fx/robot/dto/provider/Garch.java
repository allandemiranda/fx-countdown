package br.allandemiranda.fx.robot.dto.provider;

import br.allandemiranda.fx.robot.annotation.model.GarchStationarity;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@GarchStationarity
public interface Garch {

  @PositiveOrZero
  double alpha();

  @PositiveOrZero
  double beta();

  @Positive
  double omega();

  @Positive
  double sigmaAgg();
}

package br.allandemiranda.fx.robot.dto.provider;

import br.allandemiranda.fx.robot.enums.XgBoostLabel;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.NotNull;

public interface DMatrixTrainRow extends Timeseries, DMatrixRow {

  @NotNull
  XgBoostLabel label();
}

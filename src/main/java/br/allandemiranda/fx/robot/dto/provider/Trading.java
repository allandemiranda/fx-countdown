package br.allandemiranda.fx.robot.dto.provider;

import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.enums.PositionType;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public interface Trading extends Timeseries {

  @Nullable
  DealReason dealReason();

  @NotNull
  PositionType positionType();
}

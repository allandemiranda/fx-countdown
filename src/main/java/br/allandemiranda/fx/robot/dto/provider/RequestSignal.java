package br.allandemiranda.fx.robot.dto.provider;

import br.allandemiranda.fx.robot.model.core.Tick;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface RequestSignal extends DMatrixIndicatorsRow {

  @NotNull
  @NotEmpty
  List<? extends Tick> ticks();
}

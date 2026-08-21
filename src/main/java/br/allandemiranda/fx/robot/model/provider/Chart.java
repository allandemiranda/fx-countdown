package br.allandemiranda.fx.robot.model.provider;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.core.SymbolId;
import jakarta.validation.constraints.NotNull;

public interface Chart extends SymbolId {

  @NotNull
  Timeframe timeframe();
}

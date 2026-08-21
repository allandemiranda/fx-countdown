package br.allandemiranda.fx.robot.dto.provider;

import br.allandemiranda.fx.robot.annotation.model.DMatrixRowValidate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@DMatrixRowValidate
public interface DMatrixRow extends DMatrixIndicatorsRow {

  @NotNull
  @NotEmpty
  List<? extends Candlestick> candlesticks();

  @NotNull
  Garch garch();

}

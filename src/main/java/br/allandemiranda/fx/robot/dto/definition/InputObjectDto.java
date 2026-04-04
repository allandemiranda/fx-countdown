package br.allandemiranda.fx.robot.dto.definition;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import jakarta.validation.constraints.NotNull;

public interface InputObjectDto {

  @NotNull
  ChartDto chartDto();

}

package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface InputObjectDto {

  @Valid
  @NotNull
  ChartDto chartDto();

}

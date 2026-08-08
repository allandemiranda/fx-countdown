package br.allandemiranda.fx.robot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface InputObjectDto {

  @Valid
  @NotNull
  ChartDto chartDto();

}

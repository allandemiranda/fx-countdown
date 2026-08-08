package br.allandemiranda.fx.robot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public interface ChartObjectDto extends TimeLineObjectDto {

  @NotNull
  UUID id();

  @Valid
  @NotNull
  ChartDto chartDto();

}

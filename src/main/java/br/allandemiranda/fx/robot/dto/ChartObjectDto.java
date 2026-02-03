package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
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

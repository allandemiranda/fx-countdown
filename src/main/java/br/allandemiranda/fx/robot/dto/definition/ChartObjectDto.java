package br.allandemiranda.fx.robot.dto.definition;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;

public interface ChartObjectDto {

  @NotNull
  UUID id();

  @NotNull
  ChartDto chartDto();

  @NotNull
  @PastOrPresent
  OffsetDateTime timestamp();

}

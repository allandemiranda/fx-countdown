package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;

public interface IndicatorDto extends Timeseries {

  @NotNull
  UUID id();

  @Valid
  @NotNull
  ExpertAdvisorDto expertAdvisorDto();

  @NotNull
  @PastOrPresent
  OffsetDateTime timestamp();

}

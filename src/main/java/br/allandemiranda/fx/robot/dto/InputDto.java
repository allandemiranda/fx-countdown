package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public interface InputDto {

  @NotNull
  UUID id();

  @Valid
  @NotNull
  ExpertAdvisorDto expertAdvisorDto();

}

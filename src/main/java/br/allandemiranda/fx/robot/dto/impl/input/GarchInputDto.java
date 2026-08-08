package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.InputDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.util.UUID;

public record GarchInputDto(@NotNull UUID id, @Valid @NotNull ExpertAdvisorDto expertAdvisorDto, @Positive int horizon, @Min(50) int priceSize) implements Serializable, InputDto {

}

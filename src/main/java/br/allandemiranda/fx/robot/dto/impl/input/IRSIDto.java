package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record IRSIDto(@NotNull UUID id, @Valid @NotNull ExpertAdvisorDto expertAdvisorDto, short period, @NotNull AppliedPrice applyTo) implements Serializable, InputDto {

}

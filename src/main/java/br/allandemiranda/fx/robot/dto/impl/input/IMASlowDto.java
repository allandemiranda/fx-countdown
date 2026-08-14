package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record IMASlowDto(@NotNull UUID id, @Valid @NotNull ExpertAdvisorDto expertAdvisorDto, short period, short shift, @NotNull SmoothingMethod method, @NotNull AppliedPrice applyTo) implements Serializable,
    InputDto {

}

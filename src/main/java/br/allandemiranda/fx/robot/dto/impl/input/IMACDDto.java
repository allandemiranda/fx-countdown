package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record IMACDDto(@NotNull UUID id, @Valid @NotNull ExpertAdvisorDto expertAdvisorDto, short fastEma, short slowEma, short macdSma, @NotNull AppliedPrice applyTo) implements Serializable, InputDto {

}
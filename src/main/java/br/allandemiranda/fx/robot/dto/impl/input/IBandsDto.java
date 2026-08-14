package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record IBandsDto(@NotNull UUID id, @Valid @NotNull ExpertAdvisorDto expertAdvisorDto, short period, short shift, @NotNull @PositiveOrZero BigDecimal deviations, @NotNull AppliedPrice applyTo) implements Serializable,
    InputDto {

}

package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record IStochasticDto(@NotNull UUID id, @Valid @NotNull ExpertAdvisorDto expertAdvisorDto, short kPeriod, short dPeriod, short slowing, @NotNull SmoothingMethod method, @NotNull PriceField priceField) implements
    Serializable,
    InputDto {

}

package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record PriceRiskLevelInputDto(@NotNull UUID id, @Valid @NotNull ExpertAdvisorDto expertAdvisorDto, @NotNull @Positive BigDecimal kTP, @NotNull @Positive BigDecimal kSL) implements Serializable, InputDto {

}

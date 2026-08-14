package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record XGBoostInputDto(@NotNull UUID id, @Valid @NotNull ExpertAdvisorDto expertAdvisorDto, @Positive int horizon, @Positive int maxDepth, @Positive float eta, @Positive float subsample, @Positive float colSampleByTree,
                              @Positive int minChildWeight, @PositiveOrZero float lambda, @PositiveOrZero float alpha, @Positive int rounds, @Positive @Max(100) BigDecimal minimalLevelAccepted) implements Serializable,
    InputDto {

}

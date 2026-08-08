package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDashboardCreateDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;

public record XGBoostInputCreateDto(@Positive int horizon, @Positive int maxDepth, @Positive float eta, @Positive float subsample, @Positive float colSampleByTree, @Positive int minChildWeight, @PositiveOrZero float lambda,
                                    @PositiveOrZero float alpha, @Positive @Max(100) BigDecimal minimalLevelAccepted) implements Serializable, InputDashboardCreateDto {

}

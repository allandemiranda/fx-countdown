package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.InputDashboardDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.UUID;

public record MLInputDto(@NotNull UUID id, @Valid @NotNull DashboardDto dashboardDto, @Positive int chartObjectNum, @Positive int maxDepth, @Positive float eta, @Positive float subsample, @Positive float colSampleByTree,
                         @Positive int minChildWeight, @PositiveOrZero float lambda, @PositiveOrZero float alpha) implements Serializable, InputDashboardDto {

}

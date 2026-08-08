package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.InputDashboardDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record RiskLevelInputDto(@NotNull UUID id, @Valid @NotNull DashboardDto dashboardDto, @NotNull @Positive BigDecimal kTP, @NotNull @Positive BigDecimal kSL) implements Serializable, InputDashboardDto {

}

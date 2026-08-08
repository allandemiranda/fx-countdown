package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.InputDashboardDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record IRSIDto(@NotNull UUID id, @Valid @NotNull DashboardDto dashboardDto, short period, @NotNull AppliedPrice applyTo) implements Serializable, InputDashboardDto {

}

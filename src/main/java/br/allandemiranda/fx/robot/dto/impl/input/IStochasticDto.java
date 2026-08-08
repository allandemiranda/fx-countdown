package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.InputDashboardDto;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record IStochasticDto(@NotNull UUID id, @Valid @NotNull DashboardDto dashboardDto, short kPeriod, short dPeriod, short slowing, @NotNull SmoothingMethod method, @NotNull PriceField priceField) implements Serializable,
    InputDashboardDto {

}

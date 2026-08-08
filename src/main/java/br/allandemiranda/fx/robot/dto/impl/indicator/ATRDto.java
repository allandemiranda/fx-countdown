package br.allandemiranda.fx.robot.dto.impl.indicator;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.IndicatorDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ATRDto(@NotNull UUID id, @NotNull DashboardDto dashboardDto, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal atr) implements Serializable, IndicatorDto {

}
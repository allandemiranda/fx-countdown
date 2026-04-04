package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record GarchForecastDto(@NotNull UUID id, @NotNull ChartDto chartDto, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal omega, @NotNull BigDecimal alpha, @NotNull BigDecimal beta, @NotNull BigDecimal sigmaAgg) implements Serializable, BaseDto, ChartObjectDto {

}

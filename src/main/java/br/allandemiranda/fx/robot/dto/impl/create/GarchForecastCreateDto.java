package br.allandemiranda.fx.robot.dto.impl.create;

import br.allandemiranda.fx.robot.dto.CreateChartObjectDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record GarchForecastCreateDto(@NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal omega, @NotNull BigDecimal alpha, @NotNull BigDecimal beta, @NotNull BigDecimal sigmaAgg) implements Serializable, CreateChartObjectDto {

}

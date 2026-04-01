package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record GarchForecastCreateDto(@NotNull OffsetDateTime timestamp, @NotNull BigDecimal omega, @NotNull BigDecimal alpha, @NotNull BigDecimal beta, @NotNull BigDecimal sigmaAgg) implements Serializable {

}

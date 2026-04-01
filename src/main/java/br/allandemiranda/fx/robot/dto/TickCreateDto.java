package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TickCreateDto(@NotNull OffsetDateTime timestamp, @NotNull @Positive BigDecimal ask, @NotNull @Positive BigDecimal bid) implements Serializable {

}
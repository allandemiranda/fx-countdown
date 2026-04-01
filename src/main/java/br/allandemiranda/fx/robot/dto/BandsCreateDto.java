package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record BandsCreateDto(@NotNull OffsetDateTime timestamp, @NotNull BigDecimal baseLine, @NotNull BigDecimal upperBand, @NotNull BigDecimal lowerBand) implements Serializable {

}
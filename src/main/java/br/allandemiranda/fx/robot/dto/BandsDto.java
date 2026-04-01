package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record BandsDto(@NotNull UUID id, @NotNull ChartDto chartDto, @NotNull OffsetDateTime timestamp, @NotNull BigDecimal baseLine, @NotNull BigDecimal upperBand, @NotNull BigDecimal lowerBand) implements Serializable {

}
package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record CandlestickDto(@NotNull UUID id, @NotNull ChartDto chartDto, @NotNull OffsetDateTime timestamp, @NotNull @Positive BigDecimal open, @NotNull @Positive BigDecimal high, @NotNull @Positive BigDecimal low, @NotNull @Positive BigDecimal close) implements Serializable {

}
package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record MaFastCreateDto(@NotNull OffsetDateTime timestamp, @NotNull BigDecimal ma) implements Serializable {

}
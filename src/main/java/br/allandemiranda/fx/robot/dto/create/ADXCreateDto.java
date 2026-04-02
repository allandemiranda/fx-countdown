package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ADXCreateDto(@NotNull OffsetDateTime timestamp, @NotNull BigDecimal mainLine, @NotNull BigDecimal plusDiLine, @NotNull BigDecimal minusDiLine) implements Serializable, CreateChartObjectDto {

}
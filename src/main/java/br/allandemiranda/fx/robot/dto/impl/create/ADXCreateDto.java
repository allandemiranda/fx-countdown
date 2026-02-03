package br.allandemiranda.fx.robot.dto.impl.create;

import br.allandemiranda.fx.robot.dto.CreateChartObjectDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ADXCreateDto(@NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal mainLine, @NotNull BigDecimal plusDiLine, @NotNull BigDecimal minusDiLine) implements Serializable, CreateChartObjectDto {

}
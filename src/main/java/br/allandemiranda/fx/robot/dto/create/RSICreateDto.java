package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record RSICreateDto(@NotNull OffsetDateTime timestamp, @NotNull @Min(0) @Max(100) BigDecimal rsi) implements Serializable, CreateChartObjectDto {

}
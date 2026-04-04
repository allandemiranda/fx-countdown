package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record RSIDto(@NotNull UUID id, @NotNull ChartDto chartDto, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull @Min(0) @Max(100) BigDecimal rsi) implements Serializable, BaseDto, ChartObjectDto {

}
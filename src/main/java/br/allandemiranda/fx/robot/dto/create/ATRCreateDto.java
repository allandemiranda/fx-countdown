package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ATRCreateDto(@NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal atr) implements Serializable, CreateChartObjectDto {

}
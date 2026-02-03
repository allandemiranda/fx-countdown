package br.allandemiranda.fx.robot.dto.impl.base;

import br.allandemiranda.fx.robot.dto.BaseDto;
import br.allandemiranda.fx.robot.dto.ChartObjectDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record MaSlowDto(@NotNull UUID id, @Valid @NotNull ChartDto chartDto, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal ma) implements Serializable, BaseDto, ChartObjectDto {

}
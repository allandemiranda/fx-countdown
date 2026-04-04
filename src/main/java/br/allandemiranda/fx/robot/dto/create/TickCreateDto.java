package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TickCreateDto(@NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull @Positive BigDecimal ask, @NotNull @Positive BigDecimal bid) implements Serializable, CreateDto {

}
package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record BandsDto(@NotNull UUID id, @NotNull ChartDto chartDto, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal baseLine, @NotNull BigDecimal upperBand, @NotNull BigDecimal lowerBand) implements Serializable, BaseDto, ChartObjectDto {

}
package br.allandemiranda.fx.robot.dto.impl.indicator;

import br.allandemiranda.fx.robot.dto.IndicatorCreateDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record BandsCreateDto(@NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal baseLine, @NotNull BigDecimal upperBand, @NotNull BigDecimal lowerBand) implements Serializable, IndicatorCreateDto {

}
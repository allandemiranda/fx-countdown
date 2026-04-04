package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CandlestickCreateDto(@NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull @Positive BigDecimal open, @NotNull @Positive BigDecimal high, @NotNull @Positive BigDecimal low, @NotNull @Positive BigDecimal close) implements Serializable, CreateChartObjectDto {

}
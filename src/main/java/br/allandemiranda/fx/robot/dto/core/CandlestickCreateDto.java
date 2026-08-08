package br.allandemiranda.fx.robot.dto.core;

import br.allandemiranda.fx.robot.annotation.CandlestickPriceValidate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@CandlestickPriceValidate
public record CandlestickCreateDto(@NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull @Positive BigDecimal open, @NotNull @Positive BigDecimal high, @NotNull @Positive BigDecimal low,
                                   @NotNull @Positive BigDecimal close) implements Serializable, Candlestick {

}
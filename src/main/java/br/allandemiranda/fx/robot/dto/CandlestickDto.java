package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.annotation.CandlestickPriceValidate;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@CandlestickPriceValidate
public record CandlestickDto(@NotNull UUID id, @Valid @NotNull SymbolDto symbolDto, @NotNull Timeframe timeframe, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull @Positive BigDecimal open,
                             @NotNull @Positive BigDecimal high, @NotNull @Positive BigDecimal low, @NotNull @Positive BigDecimal close) implements Serializable {

}
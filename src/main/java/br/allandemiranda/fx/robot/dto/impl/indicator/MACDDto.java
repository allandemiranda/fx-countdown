package br.allandemiranda.fx.robot.dto.impl.indicator;

import br.allandemiranda.fx.robot.dto.IndicatorDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record MACDDto(@NotNull UUID id, @NotNull ExpertAdvisorDto expertAdvisorDto, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal mainLine, @NotNull BigDecimal signalLine) implements Serializable,
    IndicatorDto, MACD {

}
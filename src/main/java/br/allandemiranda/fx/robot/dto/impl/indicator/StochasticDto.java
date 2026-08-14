package br.allandemiranda.fx.robot.dto.impl.indicator;

import br.allandemiranda.fx.robot.dto.IndicatorDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record StochasticDto(@NotNull UUID id, @NotNull ExpertAdvisorDto expertAdvisorDto, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull @Min(0) @Max(100) BigDecimal mainLine,
                            @NotNull @Min(0) @Max(100) BigDecimal signalLine) implements Serializable, IndicatorDto, Stochastic {

}
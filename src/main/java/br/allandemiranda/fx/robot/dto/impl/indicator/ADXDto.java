package br.allandemiranda.fx.robot.dto.impl.indicator;

import br.allandemiranda.fx.robot.dto.IndicatorDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ADXDto(@NotNull UUID id, @NotNull ExpertAdvisorDto expertAdvisorDto, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull BigDecimal mainLine, @NotNull BigDecimal plusDiLine,
                     @NotNull BigDecimal minusDiLine) implements
    Serializable, IndicatorDto, ADX {

}
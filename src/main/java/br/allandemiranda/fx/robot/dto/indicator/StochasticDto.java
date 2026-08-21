package br.allandemiranda.fx.robot.dto.indicator;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.indicator.Stochastic;
import br.allandemiranda.fx.robot.model.indicator.provider.Indicator;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record StochasticDto(
    UUID id,
    String eaName,
    String symbolName,
    Timeframe timeframe,
    OffsetDateTime timestamp,
    BigDecimal mainLine,
    BigDecimal signalLine
) implements Serializable, Indicator, Stochastic {

}
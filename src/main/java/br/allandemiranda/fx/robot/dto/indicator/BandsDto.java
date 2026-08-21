package br.allandemiranda.fx.robot.dto.indicator;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.indicator.Bands;
import br.allandemiranda.fx.robot.model.indicator.provider.Indicator;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record BandsDto(
    UUID id,
    String eaName,
    String symbolName,
    Timeframe timeframe,
    OffsetDateTime timestamp,
    BigDecimal baseLine,
    BigDecimal upperBand,
    BigDecimal lowerBand
) implements Serializable, Indicator, Bands {

}

package br.allandemiranda.fx.robot.dto.indicator;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.indicator.ATR;
import br.allandemiranda.fx.robot.model.indicator.provider.Indicator;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ATRDto(
    UUID id,
    String eaName,
    String symbolName,
    Timeframe timeframe,
    OffsetDateTime timestamp,
    BigDecimal atr
) implements Serializable, Indicator, ATR {

}

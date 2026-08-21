package br.allandemiranda.fx.robot.dto.indicator.create.impl;

import br.allandemiranda.fx.robot.dto.indicator.create.IndicatorCreate;
import br.allandemiranda.fx.robot.model.indicator.ATR;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ATRCreateDto(
    OffsetDateTime timestamp,
    BigDecimal atr
) implements Serializable, IndicatorCreate, ATR {

}

package br.allandemiranda.fx.robot.dto.indicator.create.impl;

import br.allandemiranda.fx.robot.dto.indicator.create.IndicatorCreate;
import br.allandemiranda.fx.robot.model.indicator.ADX;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ADXCreateDto(
    OffsetDateTime timestamp,
    BigDecimal mainLine,
    BigDecimal plusDiLine,
    BigDecimal minusDiLine
) implements Serializable, IndicatorCreate, ADX {

}
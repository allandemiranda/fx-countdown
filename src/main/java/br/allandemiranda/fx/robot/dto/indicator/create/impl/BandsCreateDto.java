package br.allandemiranda.fx.robot.dto.indicator.create.impl;

import br.allandemiranda.fx.robot.dto.indicator.create.IndicatorCreate;
import br.allandemiranda.fx.robot.model.indicator.Bands;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record BandsCreateDto(
    OffsetDateTime timestamp,
    BigDecimal baseLine,
    BigDecimal upperBand,
    BigDecimal lowerBand
) implements Serializable, IndicatorCreate, Bands {

}
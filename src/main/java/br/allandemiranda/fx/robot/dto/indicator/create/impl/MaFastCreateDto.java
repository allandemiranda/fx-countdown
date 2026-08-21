package br.allandemiranda.fx.robot.dto.indicator.create.impl;

import br.allandemiranda.fx.robot.dto.indicator.create.IndicatorCreate;
import br.allandemiranda.fx.robot.model.indicator.MaFast;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record MaFastCreateDto(
    OffsetDateTime timestamp,
    BigDecimal ma
) implements Serializable, IndicatorCreate, MaFast {

}
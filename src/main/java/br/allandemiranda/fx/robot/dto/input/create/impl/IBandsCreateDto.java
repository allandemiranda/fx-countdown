package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.input.IBands;
import java.io.Serializable;
import java.math.BigDecimal;

public record IBandsCreateDto(
    short bandsPeriod,
    short bandsShift,
    BigDecimal deviation,
    AppliedPrice appliedPrice
) implements Serializable, InputCreate, IBands {

}

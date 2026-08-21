package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.input.IMACD;
import java.io.Serializable;

public record IMACDCreateDto(
    short fastEmaPeriod,
    short slowEmaPeriod,
    short signalPeriod,
    AppliedPrice appliedPrice
) implements Serializable, InputCreate, IMACD {

}

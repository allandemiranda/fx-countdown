package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.input.IRSI;
import java.io.Serializable;

public record IRSICreateDto(
    short maPeriod,
    AppliedPrice appliedPrice
) implements Serializable, InputCreate, IRSI {

}

package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.input.IMAFast;
import java.io.Serializable;

public record IMAFastCreateDto(
    short maPeriod,
    short maShift,
    SmoothingMethod maMethod,
    AppliedPrice appliedPrice
) implements Serializable, InputCreate, IMAFast {

}

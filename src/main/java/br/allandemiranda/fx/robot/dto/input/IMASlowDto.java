package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.input.IMASlow;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;

public record IMASlowDto(
    UUID id,
    String eaName,
    short maPeriod,
    short maShift,
    SmoothingMethod maMethod,
    AppliedPrice appliedPrice
) implements Serializable, Input, IMASlow {

}

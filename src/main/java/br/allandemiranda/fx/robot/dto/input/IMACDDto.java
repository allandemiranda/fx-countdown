package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.input.IMACD;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;

public record IMACDDto(
    UUID id,
    String eaName,
    short fastEmaPeriod,
    short slowEmaPeriod,
    short signalPeriod,
    AppliedPrice appliedPrice
) implements Serializable, Input, IMACD {

}
package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.input.IRSI;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;

public record IRSIDto(
    UUID id,
    String eaName,
    short maPeriod,
    AppliedPrice appliedPrice
) implements Serializable, Input, IRSI {

}

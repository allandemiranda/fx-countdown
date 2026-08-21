package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.input.IBands;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record IBandsDto(
    UUID id,
    String eaName,
    short bandsPeriod,
    short bandsShift,
    BigDecimal deviation,
    AppliedPrice appliedPrice
) implements Serializable, Input, IBands {

}

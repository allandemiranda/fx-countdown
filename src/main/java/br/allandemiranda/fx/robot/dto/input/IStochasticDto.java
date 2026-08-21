package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.input.IStochastic;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;

public record IStochasticDto(
    UUID id,
    String eaName,
    short kPeriod,
    short dPeriod,
    short slowing,
    SmoothingMethod maMethod,
    PriceField priceField
) implements Serializable, Input, IStochastic {

}

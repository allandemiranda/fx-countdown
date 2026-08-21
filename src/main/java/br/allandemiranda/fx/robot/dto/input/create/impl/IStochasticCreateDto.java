package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.input.IStochastic;
import java.io.Serializable;

public record IStochasticCreateDto(
    short kPeriod,
    short dPeriod,
    short slowing,
    SmoothingMethod maMethod,
    PriceField priceField
) implements Serializable, InputCreate, IStochastic {

}

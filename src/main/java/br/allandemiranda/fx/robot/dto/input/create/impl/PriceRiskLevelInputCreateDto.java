package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.model.input.PriceRiskLevelInput;
import java.io.Serializable;
import java.math.BigDecimal;

public record PriceRiskLevelInputCreateDto(
    BigDecimal kTP,
    BigDecimal kSL
) implements Serializable, InputCreate, PriceRiskLevelInput {

}

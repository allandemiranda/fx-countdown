package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.model.input.PriceRiskLevelInput;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record PriceRiskLevelInputDto(
    UUID id,
    String eaName,
    BigDecimal kTP,
    BigDecimal kSL
) implements Serializable, Input, PriceRiskLevelInput {

}

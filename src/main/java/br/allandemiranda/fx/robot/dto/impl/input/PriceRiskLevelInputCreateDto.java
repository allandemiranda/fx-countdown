package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputCreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

public record PriceRiskLevelInputCreateDto(@NotNull @Positive BigDecimal kTP, @NotNull @Positive BigDecimal kSL) implements Serializable, InputCreateDto {

}

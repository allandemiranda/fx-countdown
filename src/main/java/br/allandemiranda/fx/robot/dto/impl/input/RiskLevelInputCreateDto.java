package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDashboardCreateDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

public record RiskLevelInputCreateDto(@NotNull @Positive BigDecimal kTP, @NotNull @Positive BigDecimal kSL) implements Serializable, InputDashboardCreateDto {

}

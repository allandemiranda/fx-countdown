package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;

public record IBandsDto(@NotNull ChartDto chartDto, short period, short shift, @NotNull @PositiveOrZero BigDecimal deviations, @NotNull AppliedPrice applyTo) implements Serializable, BaseDto, InputObjectDto {

}

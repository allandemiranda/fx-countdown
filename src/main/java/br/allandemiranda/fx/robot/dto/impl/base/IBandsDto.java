package br.allandemiranda.fx.robot.dto.impl.base;

import br.allandemiranda.fx.robot.dto.BaseDto;
import br.allandemiranda.fx.robot.dto.InputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;

public record IBandsDto(@Valid @NotNull ChartDto chartDto, short period, short shift, @NotNull @PositiveOrZero BigDecimal deviations, @NotNull AppliedPrice applyTo) implements Serializable, BaseDto, InputObjectDto {

}

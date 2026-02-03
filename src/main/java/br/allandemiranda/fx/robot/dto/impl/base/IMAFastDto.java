package br.allandemiranda.fx.robot.dto.impl.base;

import br.allandemiranda.fx.robot.dto.BaseDto;
import br.allandemiranda.fx.robot.dto.InputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IMAFastDto(@Valid @NotNull ChartDto chartDto, short period, short shift, @NotNull SmoothingMethod method, @NotNull AppliedPrice applyTo) implements Serializable, BaseDto, InputObjectDto {

}

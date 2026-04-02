package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IMAFastDto(@NotNull ChartDto chartDto, short period, short shift, @NotNull SmoothingMethod method, @NotNull AppliedPrice applyTo) implements Serializable, BaseDto, InputObjectDto {

}

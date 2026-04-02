package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IMACDDto(@NotNull ChartDto chartDto, short fastEma, short slowEma, short macdSma, @NotNull AppliedPrice applyTo) implements Serializable, BaseDto, InputObjectDto {

}
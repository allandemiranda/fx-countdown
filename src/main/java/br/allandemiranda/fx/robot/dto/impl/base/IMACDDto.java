package br.allandemiranda.fx.robot.dto.impl.base;

import br.allandemiranda.fx.robot.dto.BaseDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.InputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IMACDDto(@Valid @NotNull ChartDto chartDto, short fastEma, short slowEma, short macdSma, @NotNull AppliedPrice applyTo) implements Serializable, BaseDto, InputObjectDto {

}
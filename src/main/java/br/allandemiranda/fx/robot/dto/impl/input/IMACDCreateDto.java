package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputCreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IMACDCreateDto(short fastEma, short slowEma, short macdSma, @NotNull AppliedPrice applyTo) implements Serializable, InputCreateDto {

}
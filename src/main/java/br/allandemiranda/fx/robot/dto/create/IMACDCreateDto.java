package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IMACDCreateDto(short fastEma, short slowEma, short macdSma, @NotNull AppliedPrice applyTo) implements Serializable, CreateInputObjectDto {

}
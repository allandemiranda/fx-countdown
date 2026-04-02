package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IMASlowCreateDto(short period, short shift, @NotNull SmoothingMethod method, @NotNull AppliedPrice applyTo) implements Serializable, CreateInputObjectDto {

}

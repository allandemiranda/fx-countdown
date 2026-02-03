package br.allandemiranda.fx.robot.dto.impl.create;

import br.allandemiranda.fx.robot.dto.CreateInputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IRSICreateDto(short period, @NotNull AppliedPrice applyTo) implements Serializable, CreateInputObjectDto {

}

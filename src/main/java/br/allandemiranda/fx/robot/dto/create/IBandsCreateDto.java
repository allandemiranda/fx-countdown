package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;

public record IBandsCreateDto(short period, short shift, @NotNull @PositiveOrZero BigDecimal deviations, @NotNull AppliedPrice applyTo) implements Serializable, CreateInputObjectDto {

}

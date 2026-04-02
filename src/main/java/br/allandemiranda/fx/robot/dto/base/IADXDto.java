package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IADXDto(@NotNull ChartDto chartDto, short period) implements Serializable, BaseDto, InputObjectDto {

}

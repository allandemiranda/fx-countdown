package br.allandemiranda.fx.robot.dto.impl.base;

import br.allandemiranda.fx.robot.dto.BaseDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.InputObjectDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IADXDto(@Valid @NotNull ChartDto chartDto, short period) implements Serializable, BaseDto, InputObjectDto {

}

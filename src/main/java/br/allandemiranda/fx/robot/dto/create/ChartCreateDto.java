package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record ChartCreateDto(@NotNull Timeframe period) implements Serializable, CreateDto {

}
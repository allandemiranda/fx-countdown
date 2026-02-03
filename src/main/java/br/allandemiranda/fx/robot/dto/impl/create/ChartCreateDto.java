package br.allandemiranda.fx.robot.dto.impl.create;

import br.allandemiranda.fx.robot.dto.CreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record ChartCreateDto(@NotNull Timeframe period) implements Serializable, CreateDto {

}
package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record ChartCreateDto(@NotNull Timeframe period) implements Serializable {

}
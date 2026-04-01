package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IRSIDto(@NotNull ChartDto chartDto, short period, @NotNull AppliedPrice applyTo) implements Serializable {

}

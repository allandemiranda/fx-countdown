package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IATRDto(@NotNull ChartDto chartDto, short period) implements Serializable {

}

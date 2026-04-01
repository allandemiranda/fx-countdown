package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IADXDto(@NotNull ChartDto chartDto, short period) implements Serializable {

}

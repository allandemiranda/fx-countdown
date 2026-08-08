package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

public record DashboardCreateDto(@NotNull @Pattern(regexp = "^[A-Z]{6}$") String symbolName, @NotNull Timeframe timeframe) implements Serializable {

}
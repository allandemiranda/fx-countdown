package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record DashboardDto(@NotNull UUID id, @NotNull @Pattern(regexp = "^[A-Z]{6}$") String symbolName, @NotNull Timeframe timeframe, @NotNull DashboardStatus status, @NotNull @PastOrPresent LocalDateTime updateTime) implements
    Serializable {

}
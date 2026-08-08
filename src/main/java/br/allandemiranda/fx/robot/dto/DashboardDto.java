package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public record DashboardDto(@NotNull UUID id, @NotNull @Pattern(regexp = "^[A-Z]{6}$") String symbolName, @NotNull Timeframe timeframe, @NotNull DashboardStatus status, @NotNull @PastOrPresent LocalDateTime updateTime,
                           @NotNull @PastOrPresent OffsetDateTime startScope, @NotNull @PastOrPresent OffsetDateTime endScope, @PositiveOrZero int version, @Positive @Max(100) BigDecimal minimalLevelAccepted) implements
    Serializable {

}
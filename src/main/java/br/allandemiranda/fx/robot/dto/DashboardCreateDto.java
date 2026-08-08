package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record DashboardCreateDto(@NotNull @Pattern(regexp = "^[A-Z]{6}$") String symbolName, @NotNull Timeframe timeframe, @NotNull @PastOrPresent OffsetDateTime startScope, @NotNull @PastOrPresent OffsetDateTime endScope,
                                 @Positive @Max(100) BigDecimal minimalLevelAccepted) implements Serializable {

}
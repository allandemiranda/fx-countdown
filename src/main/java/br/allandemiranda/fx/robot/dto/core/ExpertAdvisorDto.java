package br.allandemiranda.fx.robot.dto.core;

import br.allandemiranda.fx.robot.enums.ExpertAdvisorStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
public record ExpertAdvisorDto(@NotNull UUID id, @NotNull @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name, @NotNull SymbolDto symbolDto, @NotNull Timeframe timeframe, @NotNull ExpertAdvisorStatus status,
                               @NotNull @PastOrPresent LocalDateTime updateTime, @NotNull String description) implements Serializable {

}
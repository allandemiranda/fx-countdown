package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.InputDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ScopeInputDto(@NotNull UUID id, @Valid @NotNull ExpertAdvisorDto expertAdvisorDto, @PastOrPresent OffsetDateTime startScope, @NotNull @PastOrPresent OffsetDateTime endScope) implements Serializable,
    InputDto {

}

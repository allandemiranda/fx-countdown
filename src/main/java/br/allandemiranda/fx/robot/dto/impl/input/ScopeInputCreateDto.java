package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputCreateDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record ScopeInputCreateDto(@PastOrPresent OffsetDateTime startScope, @NotNull @PastOrPresent OffsetDateTime endScope) implements Serializable, InputCreateDto {

}

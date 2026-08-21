package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.model.input.ScopeInput;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ScopeInputDto(
    UUID id,
    String eaName,
    OffsetDateTime startScope,
    OffsetDateTime endScope
) implements Serializable, Input, ScopeInput {

}

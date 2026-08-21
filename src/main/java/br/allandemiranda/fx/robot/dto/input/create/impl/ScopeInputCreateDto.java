package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.model.input.ScopeInput;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record ScopeInputCreateDto(
    OffsetDateTime startScope,
    OffsetDateTime endScope
) implements Serializable, InputCreate, ScopeInput {

}

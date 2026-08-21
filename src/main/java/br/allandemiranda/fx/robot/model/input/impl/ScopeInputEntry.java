package br.allandemiranda.fx.robot.model.input.impl;

import br.allandemiranda.fx.robot.model.input.ScopeInput;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("input_scope")
public record ScopeInputEntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("scope_start_time") OffsetDateTime startScope,
    @Column("scope_end_time") OffsetDateTime endScope
) implements Serializable, Input, ScopeInput {

}

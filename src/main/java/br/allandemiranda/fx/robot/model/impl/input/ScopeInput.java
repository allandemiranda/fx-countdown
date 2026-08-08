package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.model.InputModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("scope_input")
public record ScopeInput(@Id @Column("chart_id") @NotNull UUID id, @Column("expert_advisor_id") @NotNull UUID expertAdvisorId, @Column("scope_start_time") @NotNull @PastOrPresent OffsetDateTime startScope,
                         @Column("scope_end_time") @NotNull @PastOrPresent OffsetDateTime endScope) implements InputModel {

}

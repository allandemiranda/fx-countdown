package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.model.InputModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("i_adx")
public record IADX(@Id @Column("chart_id") @NotNull UUID id, @Column("expert_advisor_id") @NotNull UUID expertAdvisorId, @Column("adx_period") @Positive short period) implements InputModel {

}

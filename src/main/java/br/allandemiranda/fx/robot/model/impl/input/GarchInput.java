package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.model.InputModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("garch_input")
public record GarchInput(@Id @Column("chart_id") @NotNull UUID id, @Column("expert_advisor_id") @NotNull UUID expertAdvisorId, @Column("horizon") @Positive int horizon, @Column("price_size") @Min(50) int priceSize) implements
    InputModel {

}

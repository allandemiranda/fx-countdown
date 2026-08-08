package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.InputModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("i_ma_slow")
public record IMASlow(@Id @Column("chart_id") @NotNull UUID id, @Column("expert_advisor_id") @NotNull UUID expertAdvisorId, @Column("ma_period") @Positive short period, @Column("ma_shift") @PositiveOrZero short shift,
                      @Column("ma_method") @NotNull SmoothingMethod method, @Column("applied_price") @NotNull AppliedPrice applyTo) implements InputModel {

}

package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.InputModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("i_bands")
public record IBands(@Id @Column("chart_id") @NotNull UUID id, @Column("expert_advisor_id") @NotNull UUID expertAdvisorId, @Column("bands_period") @Positive short period, @Column("bands_shift") @PositiveOrZero short shift,
                     @Column("deviation") @NotNull @PositiveOrZero BigDecimal deviations, @Column("applied_price") @NotNull AppliedPrice applyTo) implements InputModel {

}

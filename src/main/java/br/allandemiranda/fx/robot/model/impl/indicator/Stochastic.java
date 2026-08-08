package br.allandemiranda.fx.robot.model.impl.indicator;

import br.allandemiranda.fx.robot.model.IndicatorModel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("indicator_stochastic")
public record Stochastic(@Id @NotNull @Column("id") UUID id, @Column("expert_advisor_id") @NotNull UUID expertAdvisorId, @NotNull @PastOrPresent @Column("timestamp") OffsetDateTime timestamp,
                         @Column("main_line") @NotNull @Max(100) @Min(0) BigDecimal mainLine, @Column("signal_line") @NotNull @Max(100) @Min(0) BigDecimal signalLine) implements IndicatorModel {

}

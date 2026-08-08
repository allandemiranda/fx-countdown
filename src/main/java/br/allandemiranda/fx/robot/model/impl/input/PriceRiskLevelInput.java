package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.model.InputModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("price_risk_level_input")
public record PriceRiskLevelInput(@Id @Column("chart_id") @NotNull UUID id, @Column("expert_advisor_id") @NotNull UUID expertAdvisorId, @Column("k_tp") @NotNull @Positive BigDecimal kTP,
                                  @Column("k_sl") @NotNull @Positive BigDecimal kSL) implements InputModel {

  // @Column("k_tp") @Positive double kTP, @Column("k_sl") @Positive double kSL
  // 1.5  → agressivo
  // 2.0  → padrão profissional
  // 2.5  → conservador
  // 3.0  → muito conservador
}

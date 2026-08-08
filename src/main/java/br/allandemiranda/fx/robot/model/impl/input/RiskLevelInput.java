package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.model.InputDashboardModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("garch_input")
public record RiskLevelInput(@Id @Column("chart_id") @NotNull UUID id, @Column("dashboard_id") @NotNull UUID dashboardId, @Column("k_tp") @NotNull @Positive BigDecimal kTP,
                             @Column("k_sl") @NotNull @Positive BigDecimal kSL) implements InputDashboardModel {

  // @Column("k_tp") @Positive double kTP, @Column("k_sl") @Positive double kSL
  // 1.5  → agressivo
  // 2.0  → padrão profissional
  // 2.5  → conservador
  // 3.0  → muito conservador
}

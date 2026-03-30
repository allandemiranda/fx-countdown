package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("garch_input")
public record GarchInput(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("horizon") @Positive int horizon, @Column("price_size") @Min(50) int priceSize, @Column("k_tp") @Positive double kTP, @Column("k_sl") @Positive double kSL) {

  // @Column("k_tp") @Positive double kTP, @Column("k_sl") @Positive double kSL
  // 1.5  → agressivo
  // 2.0  → padrão profissional
  // 2.5  → conservador
  // 3.0  → muito conservador
}

package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("garch_input")
public class GarchInput {

  @Id
  @NotNull
  @Column("id")
  private UUID id;

  @NotNull
  @Column("chart_id")
  private UUID chartId;

  @Positive
  @Column("horizon")
  private int horizon;

  @Min(50)
  @Column("price_size")
  private int priceSize;

  // 1.5  → agressivo
  // 2.0  → padrão profissional
  // 2.5  → conservador
  // 3.0  → muito conservador

  @Positive
  @Column("k_tp")
  private double kTP;

  @Positive
  @Column("k_sl")
  private double kSL;

}

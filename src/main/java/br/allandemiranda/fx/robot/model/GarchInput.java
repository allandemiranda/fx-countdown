package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.type.ChartIdentifier;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("garch_input")
public class GarchInput extends ChartIdentifier {

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

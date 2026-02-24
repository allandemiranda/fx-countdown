package br.allandemiranda.fx.robot.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Embeddable
public class GarchInputs {

  @Positive
  @Column(name = "horizon", nullable = false)
  private int horizon;

  @Min(50)
  @Column(name = "price_size", nullable = false)
  private int priceSize;

  // 1.5  → agressivo
  // 2.0  → padrão profissional
  // 2.5  → conservador
  // 3.0  → muito conservador

  @Positive
  @Column(name = "k_tp", nullable = false)
  private double kTP;

  @Positive
  @Column(name = "k_sl", nullable = false)
  private double kSL;

}

package br.allandemiranda.fx.robot.model.embeddable;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Embeddable
public class IMACD {

  @Positive
  @Column(nullable = false, updatable = false, name = "fast_ema_period", comment = "period for Fast average calculation")
  private short fastEma;

  @Positive
  @Column(nullable = false, updatable = false, name = "slow_ema_period", comment = "period for Slow average calculation")
  private short slowEma;

  @Positive
  @Column(nullable = false, updatable = false, name = "signal_period", comment = "period for their difference averaging")
  private short macdSma;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "applied_price", comment = "type of price or handle")
  private AppliedPrice applyTo;

}

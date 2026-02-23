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
public class IRSI {

  @Positive
  @Column(nullable = false, updatable = false, name = "ma_period", comment = "averaging period")
  private short period;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "applied_price", comment = "type of price or handle")
  private AppliedPrice applyTo;

}

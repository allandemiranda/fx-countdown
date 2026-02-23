package br.allandemiranda.fx.robot.model.embeddable;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Embeddable
public class IBands {

  @Positive
  @Column(nullable = false, updatable = false, name = "bands_period", comment = "period for average line calculation")
  private short period;

  @PositiveOrZero
  @Column(nullable = false, updatable = false, name = "bands_shift", comment = "horizontal shift of the indicator")
  private short shift;

  @PositiveOrZero
  @Column(nullable = false, updatable = false, name = "deviation", comment = "number of standard deviations")
  private double deviations;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "applied_price", comment = "type of price or handle")
  private AppliedPrice applyTo;

}

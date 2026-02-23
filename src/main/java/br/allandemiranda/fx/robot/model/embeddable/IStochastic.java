package br.allandemiranda.fx.robot.model.embeddable;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Embeddable
public class IStochastic {

  @Positive
  @Column(nullable = false, updatable = false, name = "Kperiod", comment = "K-period (number of bars for calculations)")
  private short kPeriod;

  @Positive
  @Column(nullable = false, updatable = false, name = "Dperiod", comment = "D-period (period of first smoothing)")
  private short dPeriod;

  @Positive
  @Column(nullable = false, updatable = false, name = "slowing", comment = "final smoothing")
  private short slowing;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "ma_method", comment = "type of smoothing")
  private SmoothingMethod method;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "price_field", comment = "stochastic calculation method")
  private PriceField priceField;

}

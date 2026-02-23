package br.allandemiranda.fx.robot.model.embeddable;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Embeddable
public class IMA {

  @Positive
  @Column(nullable = false, updatable = false, name = "ma_period", comment = "averaging period")
  private short period;

  @PositiveOrZero
  @Column(nullable = false, updatable = false, name = "ma_shift", comment = "horizontal shift")
  private short shift;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "ma_method", comment = "smoothing type")
  private SmoothingMethod method;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "applied_price", comment = "type of price or handle")
  private AppliedPrice applyTo;

}

package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.type.TechnicalIndicator;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;

@Data
@EqualsAndHashCode(callSuper = true)
public class IStochastic extends TechnicalIndicator {

  @Positive
  @Column("Kperiod")
  private short kPeriod;          // K-period (number of bars for calculations)

  @Positive
  @Column("Dperiod")
  private short dPeriod;          // D-period (period of first smoothing)

  @Positive
  @Column("slowing")
  private short slowing;          // final smoothing

  @Column("ma_method")
  private SmoothingMethod method; // type of smoothing

  @Column("price_field")
  private PriceField priceField;  // stochastic calculation method

}

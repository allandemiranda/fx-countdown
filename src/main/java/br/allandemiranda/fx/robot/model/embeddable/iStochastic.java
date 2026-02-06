package br.allandemiranda.fx.robot.model.embeddable;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Embeddable
public class iStochastic {

  @Positive
  @Column(nullable = false, updatable = false, name = "Kperiod", comment = "K-period (number of bars for calculations)")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short kPeriod;

  @Positive
  @Column(nullable = false, updatable = false, name = "Dperiod", comment = "D-period (period of first smoothing)")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short dPeriod;

  @Positive
  @Column(nullable = false, updatable = false, name = "slowing", comment = "final smoothing")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short slowing;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "ma_method", comment = "type of smoothing")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private SmoothingMethod maMethod;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "price_field", comment = "stochastic calculation method")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private PriceField priceField;



}

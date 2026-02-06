package br.allandemiranda.fx.robot.model.embeddable;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
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
public class iMACD {

  @Positive
  @Column(nullable = false, updatable = false, name = "fast_ema_period", comment = "period for Fast average calculation")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short fastEmaPeriod;

  @Positive
  @Column(nullable = false, updatable = false, name = "slow_ema_period", comment = "period for Slow average calculation")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short slowEmaPeriod;

  @Positive
  @Column(nullable = false, updatable = false, name = "signal_period", comment = "period for their difference averaging")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short signalPeriod;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "applied_price", comment = "type of price or handle")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private AppliedPrice appliedPrice;

}

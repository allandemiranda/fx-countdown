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
public class iRSI {

  @Positive
  @Column(nullable = false, updatable = false, name = "ma_period", comment = "averaging period")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short maPeriod;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "applied_price", comment = "type of price or handle")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private AppliedPrice appliedPrice;

}

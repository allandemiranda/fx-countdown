package br.allandemiranda.fx.robot.model.embeddable;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Embeddable
public class IMA implements Serializable {

  @Positive
  @Column(nullable = false, updatable = false, name = "ma_period", comment = "averaging period")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short period;

  @PositiveOrZero
  @Column(nullable = false, updatable = false, name = "ma_shift", comment = "horizontal shift")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short shift;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "ma_method", comment = "smoothing type")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private SmoothingMethod method;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false, name = "applied_price", comment = "type of price or handle")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private AppliedPrice applyTo;

}

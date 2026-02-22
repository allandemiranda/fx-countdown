package br.allandemiranda.fx.robot.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Embeddable
public class LMBuildResult {

  @Column(nullable = false, updatable = false, comment = "Result of buy operation in points")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal buyPoints;

  @Column(nullable = false, updatable = false, comment = "Result of sell operation in points")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal sellPoints;

}

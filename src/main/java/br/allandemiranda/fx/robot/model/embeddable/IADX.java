package br.allandemiranda.fx.robot.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Embeddable
public class IADX implements Serializable {

  @Positive
  @Column(nullable = false, updatable = false, name = "adx_period", comment = "averaging period")
  @JdbcTypeCode(SqlTypes.SMALLINT)
  private short period;

}

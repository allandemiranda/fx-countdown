package br.allandemiranda.fx.robot.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Embeddable
public class LMDataBuildGarch {

  // Fundamental variables for the decision to open a trade

  @Column
  @JdbcTypeCode(SqlTypes.DOUBLE)
  @PositiveOrZero
  private double tpPips;

  @Column
  @JdbcTypeCode(SqlTypes.DOUBLE)
  @PositiveOrZero
  private double slPips;

  @Column
  @JdbcTypeCode(SqlTypes.DOUBLE)
  @PositiveOrZero
  private double sigmaPerBarPips;

  // Important variables to understand the regime

  @Column
  @JdbcTypeCode(SqlTypes.DOUBLE)
  private double mu;

  @Column
  @JdbcTypeCode(SqlTypes.DOUBLE)
  @PositiveOrZero
  private double omega;

  @Column
  @JdbcTypeCode(SqlTypes.DOUBLE)
  @PositiveOrZero
  private double alpha;

  @Column
  @JdbcTypeCode(SqlTypes.DOUBLE)
  @PositiveOrZero
  private double beta;

  @Column
  @JdbcTypeCode(SqlTypes.DOUBLE)
  @PositiveOrZero
  private double nu;

  @Column
  @JdbcTypeCode(SqlTypes.DOUBLE)
  @PositiveOrZero
  private double breakEvenHitRate;

}

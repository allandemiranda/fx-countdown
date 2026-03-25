package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.type.ChartObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("garch_forecast")
public class GarchForecast extends ChartObject {

  @Column("omega")
  private double omega;

  @Column("alpha")
  private double alpha;

  @Column("beta")
  private double beta;

  @Column("sigma_agg")
  private double sigmaAgg; // sqrt(sum sigmaNext^2)

}

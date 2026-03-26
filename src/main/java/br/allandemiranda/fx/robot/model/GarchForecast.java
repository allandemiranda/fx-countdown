package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("garch_forecast")
public class GarchForecast {

  @Id
  @NotNull
  @Column("id")
  private UUID id;

  @NotNull
  @Column("chart_id")
  private UUID chartId;

  @NotNull
  @PastOrPresent
  @Column("timestamp")
  private OffsetDateTime timestamp;

  @Column("omega")
  private double omega;

  @Column("alpha")
  private double alpha;

  @Column("beta")
  private double beta;

  @Column("sigma_agg")
  private double sigmaAgg; // sqrt(sum sigmaNext^2)

}

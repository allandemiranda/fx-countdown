package br.allandemiranda.fx.robot.model.type;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public abstract class ChartIdentifier {

  @Id
  @NotNull
  @Column("id")
  private UUID id;

  @NotNull
  @Column("chart_id")
  private UUID chartId;

}

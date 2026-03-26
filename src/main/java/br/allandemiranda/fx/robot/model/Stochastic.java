package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("indicator_stochastic")
public class Stochastic {

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

  @NotNull
  @Max(100)
  @Min(0)
  @Column("main_line")
  private BigDecimal mainLine;

  @NotNull
  @Max(100)
  @Min(0)
  @Column("signal_line")
  private BigDecimal signalLine;

}

package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.annotation.AskBidValidate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AskBidValidate
@Table("tick")
public class Tick {

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
  @Positive
  @Column("ask")
  private BigDecimal ask;

  @NotNull
  @Positive
  @Column("bid")
  private BigDecimal bid;

}

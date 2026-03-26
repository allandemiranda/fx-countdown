package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("i_rsi")
public class IRSI {

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

  @Positive
  @Column("ma_period")
  private short period;         // averaging period

  @Column("applied_price")
  private AppliedPrice applyTo; // type of price or handle

}

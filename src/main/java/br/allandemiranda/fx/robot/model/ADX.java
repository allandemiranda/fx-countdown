package br.allandemiranda.fx.robot.model;

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
@Table("indicator_adx")
public class ADX {

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
  @Column("main_line")
  private BigDecimal mainLine;

  @NotNull
  @Column("plus_di_line")
  private BigDecimal plusDiLine;

  @NotNull
  @Column("mins_di_line")
  private BigDecimal minusDiLine;

}

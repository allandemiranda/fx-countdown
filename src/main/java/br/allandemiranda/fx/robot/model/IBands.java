package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("i_bands")
public class IBands {

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
  @Column("bands_period")
  private short period;         // period for average line calculation

  @PositiveOrZero
  @Column("bands_shift")
  private short shift;          // horizontal shift of the indicator

  @PositiveOrZero
  @Column("deviation")
  private double deviations;    // number of standard deviations

  @NotNull
  @Column("applied_price")
  private AppliedPrice applyTo; // type of price or handle

}

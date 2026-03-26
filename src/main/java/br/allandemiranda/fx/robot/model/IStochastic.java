package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
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
@Table("i_stochastic")
public class IStochastic {

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
  @Column("Kperiod")
  private short kPeriod;          // K-period (number of bars for calculations)

  @Positive
  @Column("Dperiod")
  private short dPeriod;          // D-period (period of first smoothing)

  @Positive
  @Column("slowing")
  private short slowing;          // final smoothing

  @Column("ma_method")
  private SmoothingMethod method; // type of smoothing

  @Column("price_field")
  private PriceField priceField;  // stochastic calculation method

}

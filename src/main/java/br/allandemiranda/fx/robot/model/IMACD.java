package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.type.TechnicalIndicator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;

@Data
@EqualsAndHashCode(callSuper = true)
public class IMACD extends TechnicalIndicator {

  @Positive
  @Column("fast_ema_period")
  private short fastEma;        // period for Fast average calculation

  @Positive
  @Column("slow_ema_period")
  private short slowEma;        // period for Slow average calculation

  @Positive
  @Column("signal_period")
  private short macdSma;        // period for their difference averaging

  @NotNull
  @Column("applied_price")
  private AppliedPrice applyTo; // type of price or handle

}

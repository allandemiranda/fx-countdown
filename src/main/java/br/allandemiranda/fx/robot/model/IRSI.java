package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.type.TechnicalIndicator;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;

@Data
@EqualsAndHashCode(callSuper = true)
public class IRSI extends TechnicalIndicator {

  @Positive
  @Column("ma_period")
  private short period;         // averaging period

  @Column("applied_price")
  private AppliedPrice applyTo; // type of price or handle

}

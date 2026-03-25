package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.type.TechnicalIndicator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;

@Data
@EqualsAndHashCode(callSuper = true)
public class IMA extends TechnicalIndicator {

  @Positive
  @Column("ma_period")
  private short period;           // averaging period

  @PositiveOrZero
  @Column("ma_shift")
  private short shift;            // horizontal shift

  @NotNull
  @Column("ma_method")
  private SmoothingMethod method; // smoothing type

  @NotNull
  @Column("applied_price")
  private AppliedPrice applyTo;   // type of price or handle

}

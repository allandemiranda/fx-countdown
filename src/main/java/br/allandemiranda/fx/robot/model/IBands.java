package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.type.TechnicalIndicator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;

@Data
@EqualsAndHashCode(callSuper = true)
public class IBands extends TechnicalIndicator {

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

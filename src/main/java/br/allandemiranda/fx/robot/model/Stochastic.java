package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.type.ChartObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("indicator_stochastic")
public class Stochastic extends ChartObject {

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

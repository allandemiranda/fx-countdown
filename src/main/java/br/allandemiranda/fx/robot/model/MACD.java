package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.type.ChartObject;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("indicator_macd")
public class MACD extends ChartObject {

  @NotNull
  @Column("main_line")
  private BigDecimal mainLine;

  @NotNull
  @Column("signal_line")
  private BigDecimal signalLine;

}

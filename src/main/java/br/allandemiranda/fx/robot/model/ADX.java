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
@Table("indicator_adx")
public class ADX extends ChartObject {

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

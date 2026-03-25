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
@Table("indicator_bands")
public class Bands extends ChartObject {

  @NotNull
  @Column("base_line")
  private BigDecimal baseLine;

  @NotNull
  @Column("upper_band")
  private BigDecimal upperBand;

  @NotNull
  @Column("lower_band")
  private BigDecimal lowerBand;

}

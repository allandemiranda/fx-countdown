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
@Table("indicator_ma_slow")
public class MaSlow extends ChartObject {

  @NotNull
  @Column("ma")
  private BigDecimal ma;

}

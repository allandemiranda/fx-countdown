package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.annotation.AskBidValidate;
import br.allandemiranda.fx.robot.model.type.ChartObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@AskBidValidate
@Table("tick")
public class Tick extends ChartObject {

  @NotNull
  @Positive
  @Column("ask")
  private BigDecimal ask;

  @NotNull
  @Positive
  @Column("bid")
  private BigDecimal bid;

}

package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.type.ChartObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("candlestick")
public class Candlestick extends ChartObject {

  @NotNull
  @Positive
  @Column("open")
  private double open;

  @NotNull
  @Positive
  @Column("high")
  private double high;

  @NotNull
  @Positive
  @Column("low")
  private double low;

  @NotNull
  @Positive
  @Column("close")
  private double close;

}

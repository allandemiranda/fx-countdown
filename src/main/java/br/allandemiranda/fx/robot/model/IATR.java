package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.type.TechnicalIndicator;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;

@Data
@EqualsAndHashCode(callSuper = true)
public class IATR extends TechnicalIndicator {

  @Positive
  @Column("ma_period")
  private short period; // averaging period

}

package br.allandemiranda.fx.robot.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Embeddable
public class IATR {

  @Positive
  @Column(nullable = false, updatable = false, name = "ma_period", comment = "averaging period ")
  private short period;

}

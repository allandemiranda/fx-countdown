package br.allandemiranda.fx.robot.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Embeddable
public class LMDateBuildRiskLevel {

  @Column
  @Positive
  private double openPrice;

  @Column
  @Positive
  private double buyTpPrice;

  @Column
  @Positive
  private double buySlPrice;

  @Column
  @Positive
  private double sellTpPrice;

  @Column
  @Positive
  private double sellSlPrice;

}

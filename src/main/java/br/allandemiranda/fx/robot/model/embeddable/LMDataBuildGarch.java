package br.allandemiranda.fx.robot.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LMDataBuildGarch {

  @Column
  private double omega;

  @Column
  private double alpha;

  @Column
  private double beta;

  @Column
  private double sigmaAgg;

}

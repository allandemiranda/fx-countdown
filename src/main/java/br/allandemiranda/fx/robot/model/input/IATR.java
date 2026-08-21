package br.allandemiranda.fx.robot.model.input;

import jakarta.validation.constraints.Positive;

public interface IATR {

  @Positive
  short maPeriod();
}

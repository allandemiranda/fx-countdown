package br.allandemiranda.fx.robot.model.input;

import jakarta.validation.constraints.Positive;

public interface IADX {

  @Positive
  short adxPeriod();
}

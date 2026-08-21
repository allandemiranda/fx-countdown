package br.allandemiranda.fx.robot.model.ea;

import jakarta.validation.constraints.NotNull;

public interface ExpertAdvisorParameters {

  @NotNull
  String description();
}

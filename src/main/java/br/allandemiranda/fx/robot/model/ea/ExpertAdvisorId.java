package br.allandemiranda.fx.robot.model.ea;

import br.allandemiranda.fx.robot.annotation.field.EaName;
import jakarta.validation.constraints.NotNull;

public interface ExpertAdvisorId {

  @NotNull
  @EaName
  String eaName();

}

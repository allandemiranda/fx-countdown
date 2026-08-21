package br.allandemiranda.fx.robot.model.analysis;

import br.allandemiranda.fx.robot.annotation.field.EaName;
import jakarta.validation.constraints.NotNull;

public interface XgBoostEa {

  @NotNull
  @EaName
  String eaName();

}

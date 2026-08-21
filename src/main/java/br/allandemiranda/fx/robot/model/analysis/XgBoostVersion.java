package br.allandemiranda.fx.robot.model.analysis;

import br.allandemiranda.fx.robot.annotation.field.Version;
import jakarta.validation.constraints.NotNull;

public interface XgBoostVersion {

  @NotNull
  @Version
  String version();

}

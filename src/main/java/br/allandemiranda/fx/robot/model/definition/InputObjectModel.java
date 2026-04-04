package br.allandemiranda.fx.robot.model.definition;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public interface InputObjectModel {

  @NotNull
  UUID chartId();

}

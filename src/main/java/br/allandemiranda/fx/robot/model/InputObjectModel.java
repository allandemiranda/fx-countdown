package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public interface InputObjectModel {

  @NotNull
  UUID chartId();

}

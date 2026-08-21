package br.allandemiranda.fx.robot.model.provider;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public interface DefaultId {

  @NotNull
  UUID id();
}

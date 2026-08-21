package br.allandemiranda.fx.robot.model.input;

import br.allandemiranda.fx.robot.annotation.model.ScopeValidate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;

@ScopeValidate
public interface ScopeInput {

  @NotNull
  @PastOrPresent
  OffsetDateTime endScope();

  @NotNull
  @PastOrPresent
  OffsetDateTime startScope();
}

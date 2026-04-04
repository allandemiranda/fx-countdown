package br.allandemiranda.fx.robot.model.definition;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;

public interface ChartObjectModel {

  @NotNull
  UUID id();

  @NotNull
  UUID chartId();

  @NotNull
  @PastOrPresent
  OffsetDateTime timestamp();

}

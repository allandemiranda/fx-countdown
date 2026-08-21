package br.allandemiranda.fx.robot.model.analysis;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;

public interface XgBoostInfo {

  @NotNull
  @PastOrPresent
  OffsetDateTime createdAt();

}

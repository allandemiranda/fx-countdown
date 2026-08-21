package br.allandemiranda.fx.robot.model.ea;

import br.allandemiranda.fx.robot.enums.EAStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;

public interface ExpertAdvisorStatus {

  @NotNull
  EAStatus status();

  @NotNull
  @PastOrPresent
  OffsetDateTime updateTime();
}

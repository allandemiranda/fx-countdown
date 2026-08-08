package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;

public interface IndicatorModel {

  @Id
  @NotNull
  UUID id();

  @NotNull
  UUID dashboardId();

  @NotNull
  @PastOrPresent
  OffsetDateTime timestamp();

}

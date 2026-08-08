package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;

public interface Timeseries {

  @NotNull
  @PastOrPresent
  OffsetDateTime timestamp();
}

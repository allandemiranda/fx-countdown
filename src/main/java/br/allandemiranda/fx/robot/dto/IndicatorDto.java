package br.allandemiranda.fx.robot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;

public interface IndicatorDto {

  @NotNull
  UUID id();

  @Valid
  @NotNull
  DashboardDto dashboardDto();

  @NotNull
  @PastOrPresent
  OffsetDateTime timestamp();

}

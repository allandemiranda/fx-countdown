package br.allandemiranda.fx.robot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public interface InputDashboardDto {

  @NotNull
  UUID id();

  @Valid
  @NotNull
  DashboardDto dashboardDto();

}

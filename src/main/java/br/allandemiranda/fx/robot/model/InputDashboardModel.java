package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.springframework.data.annotation.Id;

public interface InputDashboardModel {

  @Id
  @NotNull
  UUID id();

  @NotNull
  UUID dashboardId();

}

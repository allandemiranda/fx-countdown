package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.DashboardCreateDto;
import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.model.Dashboard;
import java.time.OffsetDateTime;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class DashboardMapper {

  public static @NonNull DashboardDto toDashboardDto(@NonNull ChartDto chartDto, @NonNull Dashboard dashboard) {
    return new DashboardDto(chartDto, dashboard.status(), dashboard.updateTime(), dashboard.startScope(), dashboard.endScope());
  }

  public static @NonNull Dashboard toDashboard(@NonNull ChartDto chartDto, @NonNull DashboardCreateDto dashboardCreateDto) {
    return new Dashboard(chartDto.id(), DashboardStatus.CREATED, OffsetDateTime.now(), dashboardCreateDto.startScope(), dashboardCreateDto.endScope());
  }
}

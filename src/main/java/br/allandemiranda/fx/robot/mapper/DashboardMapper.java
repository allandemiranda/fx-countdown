package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.model.Dashboard;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;

@Component
public final class DashboardMapper {

  public DashboardDto toDto(ChartDto chartDto, Dashboard dashboard) {
    return new DashboardDto(chartDto, dashboard.status(), dashboard.updateTime(), dashboard.startScope(), dashboard.endScope());
  }

  public Dashboard toModel(ChartDto chartDto, DashboardCreateDto dashboardCreateDto, DashboardStatus status, OffsetDateTime updateTime) {
    return new Dashboard(chartDto.id(), status, updateTime, dashboardCreateDto.startScope(), dashboardCreateDto.endScope());
  }
}

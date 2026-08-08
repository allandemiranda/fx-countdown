package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.DashboardCreateDto;
import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.model.Dashboard;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

@Component
public final class DashboardMapper {

  public DashboardDto toDto(Dashboard dashboard) {
    return new DashboardDto(dashboard.id(), dashboard.symbolName(), dashboard.timeframe(), dashboard.status(), dashboard.updateTime(), dashboard.startScope(), dashboard.endScope(), dashboard.version(),
        dashboard.minimalLevelAccepted());
  }

  public Dashboard toModel(DashboardCreateDto dashboardCreateDto) {
    return new Dashboard(null, dashboardCreateDto.symbolName(), dashboardCreateDto.timeframe(), DashboardStatus.CREATED, LocalDateTime.now(ZoneId.systemDefault()), dashboardCreateDto.startScope(), dashboardCreateDto.endScope(),
        0, dashboardCreateDto.minimalLevelAccepted());
  }
}

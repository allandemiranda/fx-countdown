package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.impl.Dashboard;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

@Component
public final class DashboardMapper implements InputObjectMapper<Dashboard, DashboardDto, DashboardCreateDto> {

  public DashboardDto toDto(ChartDto chartDto, Dashboard dashboard) {
    return new DashboardDto(chartDto, dashboard.status(), dashboard.updateTime(), dashboard.startScope(), dashboard.endScope(), dashboard.fileName());
  }

  public Dashboard toModel(ChartDto chartDto, DashboardCreateDto dashboardCreateDto) {
    return new Dashboard(chartDto.id(), dashboardCreateDto.status(), OffsetDateTime.now(ZoneId.systemDefault()), dashboardCreateDto.startScope(), dashboardCreateDto.endScope(), dashboardCreateDto.fileName());
  }
}

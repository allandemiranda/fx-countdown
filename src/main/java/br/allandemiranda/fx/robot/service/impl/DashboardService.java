package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.mapper.impl.DashboardMapper;
import br.allandemiranda.fx.robot.model.impl.Dashboard;
import br.allandemiranda.fx.robot.repository.impl.DashboardRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Getter
@Service
public class DashboardService implements InputObjectService<Dashboard, DashboardDto, DashboardCreateDto> {

  private final DashboardRepository repository;
  private final DashboardMapper mapper;

  public Mono<DashboardDto> updateStatus(ChartDto chartDto, DashboardStatus status) {
    log.info("Update Status [chartDto={}, status={}]", chartDto, status);
    return this.get(chartDto).map(dashboardDto -> new Dashboard(dashboardDto.chartDto().id(), status, OffsetDateTime.now(ZoneId.systemDefault()), dashboardDto.startScope(), dashboardDto.endScope(), dashboardDto.fileName()))
        .flatMap(dashboard -> this.getRepository().save(dashboard).map(model -> this.getMapper().toDto(chartDto, model)));
  }

  public Mono<DashboardDto> updateScope(ChartDto chartDto, OffsetDateTime startScope, OffsetDateTime endScope) {
    log.info("Update Scope [chartDto={}, startScope={}, endScope={}]", chartDto, startScope, endScope);
    return this.get(chartDto).map(dashboardDto -> new Dashboard(dashboardDto.chartDto().id(), dashboardDto.status(), OffsetDateTime.now(ZoneId.systemDefault()), startScope, endScope, dashboardDto.fileName()))
        .flatMap(dashboard -> this.getRepository().save(dashboard).map(model -> this.getMapper().toDto(chartDto, model)));
  }
}

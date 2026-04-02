package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.dto.base.DashboardDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.mapper.DashboardMapper;
import br.allandemiranda.fx.robot.model.Dashboard;
import br.allandemiranda.fx.robot.repository.DashboardRepository;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Service
public final class DashboardService {

  private final DashboardRepository repository;
  private final DashboardMapper mapper;

  public Mono<DashboardDto> getDashboard(ChartDto chartDto) {
    return this.getRepository().findByChartId(chartDto.id()).map(dashboard -> this.getMapper().toDto(chartDto, dashboard));
  }

  public Mono<DashboardDto> createDashboard(ChartDto chartDto, DashboardCreateDto dashboardCreateDto) {
    Dashboard model = this.getMapper().toModel(chartDto, dashboardCreateDto, DashboardStatus.CREATED, OffsetDateTime.now());
    return this.getRepository().save(model).map(dashboard -> this.getMapper().toDto(chartDto, dashboard));
  }

  public Mono<Void> deleteDashboard(ChartDto chartDto) {
    return this.getRepository().deleteByChartId(chartDto.id());
  }

}

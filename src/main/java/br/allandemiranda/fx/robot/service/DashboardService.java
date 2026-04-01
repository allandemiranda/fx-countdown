package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.DashboardCreateDto;
import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.mapper.DashboardMapper;
import br.allandemiranda.fx.robot.model.Dashboard;
import br.allandemiranda.fx.robot.repository.DashboardRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class DashboardService {

  private final DashboardRepository repository;

  @Transactional(readOnly = true)
  public Mono<DashboardDto> getDashboard(@NonNull ChartDto chartDto) {
    return this.getRepository().findDashboard(chartDto.id()).map(dashboard -> DashboardMapper.toDashboardDto(chartDto, dashboard));
  }

  public Mono<DashboardDto> createDashboard(@NonNull ChartDto chartDto, @NonNull DashboardCreateDto dashboardCreateDto) {
    Dashboard model = DashboardMapper.toDashboard(chartDto, dashboardCreateDto);
    return this.getRepository().save(model).map(dashboard -> DashboardMapper.toDashboardDto(chartDto, dashboard));
  }

  public Mono<Void> deleteDashboard(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteDashboard(chartDto.id());
  }

}

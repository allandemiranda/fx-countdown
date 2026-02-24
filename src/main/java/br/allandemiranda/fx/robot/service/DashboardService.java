package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.DashboardMapper;
import br.allandemiranda.fx.robot.repository.DashboardRepository;
import java.util.Collection;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class DashboardService {

  private final DashboardRepository dashboardRepository;
  private final DashboardMapper dashboardMapper;

  @Transactional(readOnly = true)
  public Collection<DashboardDto> getDashboard() {
    return this.getDashboardRepository().findAll().stream().map(dashboard -> this.getDashboardMapper().toDto(dashboard)).toList();
  }

  @Transactional(readOnly = true)
  public Optional<DashboardDto> getDashboard(String symbolName, Timeframe period) {
    return this.getDashboardRepository().findFirstByChart_Symbol_NameAndChart_Period(symbolName, period).map(dashboard -> this.getDashboardMapper().toDto(dashboard));
  }

  public long updateStatus(String symbolName, Timeframe period, DashboardStatus status) {
    return dashboardRepository.update((root, query, cb) -> {
      query.set(root.get("status"), status);
      return cb.and(
          cb.equal(root.get("chart").get("symbol").get("name"), symbolName),
          cb.equal(root.get("chart").get("period"), period)
      );
    });
  }

}

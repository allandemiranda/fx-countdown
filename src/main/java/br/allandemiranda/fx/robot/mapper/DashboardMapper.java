package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.model.Chart;
import br.allandemiranda.fx.robot.model.Dashboard;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING, uses = {ChartMapper.class})
public interface DashboardMapper {

  Dashboard toEntity(DashboardDto dashboardDto);

  @AfterMapping
  default void linkChart(@MappingTarget Dashboard dashboard) {
    Chart chart = dashboard.getChart();
    if (chart != null) {
      chart.setDashboard(dashboard);
    }
  }

  DashboardDto toDto(Dashboard dashboard);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Dashboard partialUpdate(DashboardDto dashboardDto, @MappingTarget Dashboard dashboard);
}
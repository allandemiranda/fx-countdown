package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.TickChartDto;
import br.allandemiranda.fx.robot.model.Chart;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface TickChartMapper {

  Chart toEntity(TickChartDto tickChartDto);

  @AfterMapping
  default void linkTicks(@MappingTarget Chart chart) {
    chart.getTicks().forEach(tick -> tick.setChart(chart));
  }

  TickChartDto toDto(Chart chart);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Chart partialUpdate(TickChartDto tickChartDto, @MappingTarget Chart chart);
}
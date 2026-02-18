package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.MaSlowCreateDto;
import br.allandemiranda.fx.robot.model.MaSlow;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface MaSlowMapper {

  @Mapping(source = "chartPeriod", target = "chart.period")
  @Mapping(source = "chartSymbolName", target = "chart.symbol.name")
  MaSlow toEntity(MaSlowCreateDto maSlowCreateDto);

  @InheritInverseConfiguration(name = "toEntity")
  MaSlowCreateDto toDto(MaSlow maSlow);

  @InheritConfiguration(name = "toEntity")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  MaSlow partialUpdate(MaSlowCreateDto maSlowCreateDto, @MappingTarget MaSlow maSlow);
}
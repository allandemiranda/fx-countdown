package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartCreateDto;
import br.allandemiranda.fx.robot.model.Chart;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface ChartCreateMapper {

  @Mapping(source = "symbolName", target = "symbol.name")
  Chart toEntity(ChartCreateDto chartCreateDto);

  @Mapping(source = "symbol.name", target = "symbolName")
  ChartCreateDto toDto(Chart chart);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(source = "symbolName", target = "symbol.name")
  Chart partialUpdate(ChartCreateDto chartCreateDto, @MappingTarget Chart chart);
}
package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.MaFastCreateDto;
import br.allandemiranda.fx.robot.model.MaFast;
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
public interface MaFastMapper {

  @Mapping(source = "chartPeriod", target = "chart.period")
  @Mapping(source = "chartSymbolName", target = "chart.symbol.name")
  MaFast toEntity(MaFastCreateDto maFastCreateDto);

  @InheritInverseConfiguration(name = "toEntity")
  MaFastCreateDto toDto(MaFast maFast);

  @InheritConfiguration(name = "toEntity")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  MaFast partialUpdate(MaFastCreateDto maFastCreateDto, @MappingTarget MaFast maFast);
}
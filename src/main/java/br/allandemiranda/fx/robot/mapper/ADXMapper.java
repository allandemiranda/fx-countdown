package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ADXCreateDto;
import br.allandemiranda.fx.robot.model.ADX;
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
public interface ADXMapper {

  @Mapping(source = "chartPeriod", target = "chart.period")
  @Mapping(source = "chartSymbolName", target = "chart.symbol.name")
  ADX toEntity(ADXCreateDto ADXCreateDto);

  @InheritInverseConfiguration(name = "toEntity")
  ADXCreateDto toDto(ADX ADX);

  @InheritConfiguration(name = "toEntity")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  ADX partialUpdate(ADXCreateDto ADXCreateDto, @MappingTarget ADX ADX);
}
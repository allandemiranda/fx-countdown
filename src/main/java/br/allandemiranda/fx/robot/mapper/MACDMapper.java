package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.MACDCreateDto;
import br.allandemiranda.fx.robot.model.MACD;
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
public interface MACDMapper {

  @Mapping(source = "chartPeriod", target = "chart.period")
  @Mapping(source = "chartSymbolName", target = "chart.symbol.name")
  MACD toEntity(MACDCreateDto MACDCreateDto);

  @InheritInverseConfiguration(name = "toEntity")
  MACDCreateDto toDto(MACD MACD);

  @InheritConfiguration(name = "toEntity")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  MACD partialUpdate(MACDCreateDto MACDCreateDto, @MappingTarget MACD MACD);
}
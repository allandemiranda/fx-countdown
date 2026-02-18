package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ATRCreateDto;
import br.allandemiranda.fx.robot.model.ATR;
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
public interface ATRMapper {

  @Mapping(source = "chartPeriod", target = "chart.period")
  @Mapping(source = "chartSymbolName", target = "chart.symbol.name")
  ATR toEntity(ATRCreateDto ATRCreateDto);

  @InheritInverseConfiguration(name = "toEntity")
  ATRCreateDto toDto(ATR ATR);

  @InheritConfiguration(name = "toEntity")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  ATR partialUpdate(ATRCreateDto ATRCreateDto, @MappingTarget ATR ATR);
}
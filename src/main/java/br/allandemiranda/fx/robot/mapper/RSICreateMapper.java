package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.RSICreateDto;
import br.allandemiranda.fx.robot.model.RSI;
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
public interface RSICreateMapper {

  @Mapping(source = "chartPeriod", target = "chart.period")
  @Mapping(source = "chartSymbolName", target = "chart.symbol.name")
  RSI toEntity(RSICreateDto RSICreateDto);

  @InheritInverseConfiguration(name = "toEntity")
  RSICreateDto toDto(RSI RSI);

  @InheritConfiguration(name = "toEntity")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  RSI partialUpdate(RSICreateDto RSICreateDto, @MappingTarget RSI RSI);
}
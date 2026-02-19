package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.BandsCreateDto;
import br.allandemiranda.fx.robot.model.Bands;
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
public interface BandsCreateMapper {

  @Mapping(source = "chartPeriod", target = "chart.period")
  @Mapping(source = "chartSymbolName", target = "chart.symbol.name")
  Bands toEntity(BandsCreateDto bandsCreateDto);

  @InheritInverseConfiguration(name = "toEntity")
  BandsCreateDto toDto(Bands bands);

  @InheritConfiguration(name = "toEntity")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Bands partialUpdate(BandsCreateDto bandsCreateDto, @MappingTarget Bands bands);
}
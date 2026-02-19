package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.TickCreateDto;
import br.allandemiranda.fx.robot.model.Tick;
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
public interface TickCreateMapper {

  @Mapping(source = "chartPeriod", target = "chart.period")
  @Mapping(source = "chartSymbolName", target = "chart.symbol.name")
  Tick toEntity(TickCreateDto tickCreateDto);

  @InheritInverseConfiguration(name = "toEntity")
  TickCreateDto toDto(Tick tick);

  @InheritConfiguration(name = "toEntity")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Tick partialUpdate(TickCreateDto tickCreateDto, @MappingTarget Tick tick);
}
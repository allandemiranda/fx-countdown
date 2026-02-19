package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.MACDDto;
import br.allandemiranda.fx.robot.model.MACD;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface MACDMapper {

  MACD toEntity(MACDDto MACDDto);

  MACDDto toDto(MACD MACD);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  MACD partialUpdate(MACDDto MACDDto, @MappingTarget MACD MACD);
}
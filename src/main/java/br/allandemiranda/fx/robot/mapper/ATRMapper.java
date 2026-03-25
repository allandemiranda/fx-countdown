package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ATRDto;
import br.allandemiranda.fx.robot.model.AtrIndicator;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface ATRMapper {

  AtrIndicator toEntity(ATRDto ATRDto);

  ATRDto toDto(AtrIndicator ATR);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  AtrIndicator partialUpdate(ATRDto ATRDto, @MappingTarget AtrIndicator ATR);
}
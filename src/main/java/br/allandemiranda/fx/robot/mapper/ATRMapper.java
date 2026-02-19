package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ATRDto;
import br.allandemiranda.fx.robot.model.ATR;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface ATRMapper {

  ATR toEntity(ATRDto ATRDto);

  ATRDto toDto(ATR ATR);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  ATR partialUpdate(ATRDto ATRDto, @MappingTarget ATR ATR);
}
package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.MaFastDto;
import br.allandemiranda.fx.robot.model.MaFast;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface MaFastMapper {

  MaFast toEntity(MaFastDto maFastDto);

  MaFastDto toDto(MaFast maFast);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  MaFast partialUpdate(MaFastDto maFastDto, @MappingTarget MaFast maFast);
}
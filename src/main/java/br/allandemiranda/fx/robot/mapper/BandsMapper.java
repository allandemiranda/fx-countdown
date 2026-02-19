package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.BandsDto;
import br.allandemiranda.fx.robot.model.Bands;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface BandsMapper {

  Bands toEntity(BandsDto bandsDto);

  BandsDto toDto(Bands bands);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Bands partialUpdate(BandsDto bandsDto, @MappingTarget Bands bands);
}
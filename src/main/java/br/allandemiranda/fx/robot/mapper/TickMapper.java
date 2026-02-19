package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.TickCreateDto;
import br.allandemiranda.fx.robot.model.Tick;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface TickMapper {

  Tick toEntity(TickCreateDto tickCreateDto);

  TickCreateDto toDto(Tick tick);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Tick partialUpdate(TickCreateDto tickCreateDto, @MappingTarget Tick tick);
}
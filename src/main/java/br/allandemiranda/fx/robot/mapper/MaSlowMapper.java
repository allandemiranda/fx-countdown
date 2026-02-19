package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.MaSlowDto;
import br.allandemiranda.fx.robot.model.MaSlow;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface MaSlowMapper {

  MaSlow toEntity(MaSlowDto maSlowDto);

  MaSlowDto toDto(MaSlow maSlow);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  MaSlow partialUpdate(MaSlowDto maSlowDto, @MappingTarget MaSlow maSlow);
}
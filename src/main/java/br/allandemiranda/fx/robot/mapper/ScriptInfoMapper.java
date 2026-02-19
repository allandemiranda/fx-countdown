package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ScriptInfoDto;
import br.allandemiranda.fx.robot.model.ScriptInfo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface ScriptInfoMapper {

  ScriptInfo toEntity(ScriptInfoDto scriptInfoDto);

  ScriptInfoDto toDto(ScriptInfo scriptInfo);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  ScriptInfo partialUpdate(ScriptInfoDto scriptInfoDto, @MappingTarget ScriptInfo scriptInfo);
}
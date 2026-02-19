package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.RSIDto;
import br.allandemiranda.fx.robot.model.RSI;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface RSIMapper {

  RSI toEntity(RSIDto RSIDto);

  RSIDto toDto(RSI RSI);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  RSI partialUpdate(RSIDto RSIDto, @MappingTarget RSI RSI);
}
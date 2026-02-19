package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.model.Symbol;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface SymbolMapper {

  Symbol toEntity(SymbolDto symbolDto);

  SymbolDto toDto(Symbol symbol);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Symbol partialUpdate(SymbolDto symbolDto, @MappingTarget Symbol symbol);
}
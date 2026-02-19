package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.SymbolCreateDto;
import br.allandemiranda.fx.robot.model.Symbol;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface SymbolCreateMapper {

  Symbol toEntity(SymbolCreateDto symbolCreateDto);

  SymbolCreateDto toDto(Symbol symbol);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Symbol partialUpdate(SymbolCreateDto symbolCreateDto, @MappingTarget Symbol symbol);
}
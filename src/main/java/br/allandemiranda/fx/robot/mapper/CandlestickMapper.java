package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.model.Candlestick;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface CandlestickMapper {

  Candlestick toEntity(CandlestickDto candlestickDto);

  CandlestickDto toDto(Candlestick candlestick);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Candlestick partialUpdate(CandlestickDto candlestickDto, @MappingTarget Candlestick candlestick);
}
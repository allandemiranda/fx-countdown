package br.allandemiranda.fx.robot.mapper.indicator.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.ADXDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.ADXCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.IndicatorMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.ADXEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ADXMapper implements IndicatorMapper<ADXEntry, ADXDto, ADXCreateDto> {

  @Override
  public ADXDto toDto(ADXEntry model) {
    return new ADXDto(model.id(), model.eaName(), model.symbolName(), model.timeframe(), model.timestamp(), model.mainLine(), model.plusDiLine(), model.minusDiLine());
  }

  @Override
  public ADXEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, ADXCreateDto createDto) {
    return new ADXEntry(id, expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), createDto.timestamp(), createDto.mainLine(), createDto.plusDiLine(), createDto.minusDiLine());
  }
}

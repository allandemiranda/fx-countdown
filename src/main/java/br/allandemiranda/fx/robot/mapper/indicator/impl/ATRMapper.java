package br.allandemiranda.fx.robot.mapper.indicator.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.ATRDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.ATRCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.IndicatorMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.ATREntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ATRMapper implements IndicatorMapper<ATREntry, ATRDto, ATRCreateDto> {

  @Override
  public ATRDto toDto(ATREntry model) {
    return new ATRDto(model.id(), model.eaName(), model.symbolName(), model.timeframe(), model.timestamp(), model.atr());
  }

  @Override
  public ATREntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, ATRCreateDto createDto) {
    return new ATREntry(id, expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), createDto.timestamp(), createDto.atr());
  }
}

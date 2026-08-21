package br.allandemiranda.fx.robot.mapper.indicator.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.MACDDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.MACDCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.IndicatorMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.MACDEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MACDMapper implements IndicatorMapper<MACDEntry, MACDDto, MACDCreateDto> {

  @Override
  public MACDDto toDto(MACDEntry model) {
    return new MACDDto(model.id(), model.eaName(), model.symbolName(), model.timeframe(), model.timestamp(), model.mainLine(), model.signalLine());
  }

  @Override
  public MACDEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, MACDCreateDto createDto) {
    return new MACDEntry(id, expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), createDto.timestamp(), createDto.mainLine(), createDto.signalLine());
  }
}

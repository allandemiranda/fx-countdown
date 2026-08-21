package br.allandemiranda.fx.robot.mapper.indicator.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.StochasticDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.StochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.IndicatorMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.StochasticEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class StochasticMapper implements IndicatorMapper<StochasticEntry, StochasticDto, StochasticCreateDto> {

  @Override
  public StochasticDto toDto(StochasticEntry model) {
    return new StochasticDto(model.id(), model.eaName(), model.symbolName(), model.timeframe(), model.timestamp(), model.mainLine(), model.signalLine());
  }

  @Override
  public StochasticEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, StochasticCreateDto createDto) {
    return new StochasticEntry(id, expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), createDto.timestamp(), createDto.mainLine(), createDto.signalLine());
  }
}

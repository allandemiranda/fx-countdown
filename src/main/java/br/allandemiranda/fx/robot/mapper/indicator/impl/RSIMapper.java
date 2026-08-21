package br.allandemiranda.fx.robot.mapper.indicator.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.RSIDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.RSICreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.IndicatorMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.RSIEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class RSIMapper implements IndicatorMapper<RSIEntry, RSIDto, RSICreateDto> {

  @Override
  public RSIDto toDto(RSIEntry model) {
    return new RSIDto(model.id(), model.eaName(), model.symbolName(), model.timeframe(), model.timestamp(), model.rsi());
  }

  @Override
  public RSIEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, RSICreateDto createDto) {
    return new RSIEntry(id, expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), createDto.timestamp(), createDto.rsi());
  }
}

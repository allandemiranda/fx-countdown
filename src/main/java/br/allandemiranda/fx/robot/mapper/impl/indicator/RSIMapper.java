package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSICreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSIDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.RSI;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class RSIMapper implements IndicatorMapper<RSI, RSIDto, RSICreateDto> {

  @Override
  public RSIDto toDto(ExpertAdvisorDto expertAdvisorDto, RSI rsi) {
    return new RSIDto(rsi.id(), expertAdvisorDto, rsi.timestamp(), rsi.rsi());
  }

  @Override
  public RSI toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, RSICreateDto rsiCreateDto) {
    return new RSI(id, expertAdvisorDto.id(), rsiCreateDto.timestamp(), rsiCreateDto.rsi());
  }

}

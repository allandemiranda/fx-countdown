package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.MACD;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MACDMapper implements IndicatorMapper<MACD, MACDDto, MACDCreateDto> {

  @Override
  public MACDDto toDto(ExpertAdvisorDto expertAdvisorDto, MACD macd) {
    return new MACDDto(macd.id(), expertAdvisorDto, macd.timestamp(), macd.mainLine(), macd.signalLine());
  }

  @Override
  public MACD toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, MACDCreateDto macdCreateDto) {
    return new MACD(id, expertAdvisorDto.id(), macdCreateDto.timestamp(), macdCreateDto.mainLine(), macdCreateDto.signalLine());
  }

}

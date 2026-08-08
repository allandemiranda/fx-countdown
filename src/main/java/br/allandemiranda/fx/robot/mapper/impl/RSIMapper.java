package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.RSIDto;
import br.allandemiranda.fx.robot.dto.impl.create.RSICreateDto;
import br.allandemiranda.fx.robot.mapper.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.impl.RSI;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class RSIMapper implements ChartObjectMapper<RSI, RSIDto, RSICreateDto> {

  @Override
  public RSIDto toDto(ChartDto chartDto, RSI rsi) {
    return new RSIDto(rsi.id(), chartDto, rsi.timestamp(), rsi.rsi());
  }

  @Override
  public RSI toModel(UUID id, ChartDto chartDto, RSICreateDto rsiCreateDto) {
    return new RSI(id, chartDto.id(), rsiCreateDto.timestamp(), rsiCreateDto.rsi());
  }

}

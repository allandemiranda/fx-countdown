package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.RSICreateDto;
import br.allandemiranda.fx.robot.dto.base.RSIDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.RSI;
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

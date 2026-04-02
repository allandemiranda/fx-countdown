package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.create.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.base.CandlestickDto;
import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.Candlestick;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class CandlestickMapper implements ChartObjectMapper<Candlestick, CandlestickDto, CandlestickCreateDto> {

  @Override
  public CandlestickDto toDto(ChartDto chartDto, Candlestick candlestick) {
    return new CandlestickDto(candlestick.id(), chartDto, candlestick.timestamp(), candlestick.open(), candlestick.high(), candlestick.low(), candlestick.close());
  }

  @Override
  public Candlestick toModel(UUID id, ChartDto chartDto, CandlestickCreateDto candlestickCreateDto) {
    return new Candlestick(id, chartDto.id(), candlestickCreateDto.timestamp(), candlestickCreateDto.open(), candlestickCreateDto.high(), candlestickCreateDto.low(), candlestickCreateDto.close());
  }

}

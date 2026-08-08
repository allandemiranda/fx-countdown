package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.Candlestick;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class CandlestickMapper {

  public CandlestickDto toDto(SymbolDto symbolDto, Candlestick candlestick) {
    return new CandlestickDto(candlestick.id(), symbolDto, candlestick.timeframe(), candlestick.timestamp(), candlestick.open(), candlestick.high(), candlestick.low(), candlestick.close());
  }

  public Candlestick toModel(UUID id, SymbolDto symbolDto, Timeframe timeframe, CandlestickCreateDto candlestickCreateDto) {
    return new Candlestick(id, symbolDto.name(), timeframe, candlestickCreateDto.timestamp(), candlestickCreateDto.open(), candlestickCreateDto.high(), candlestickCreateDto.low(), candlestickCreateDto.close());
  }

}

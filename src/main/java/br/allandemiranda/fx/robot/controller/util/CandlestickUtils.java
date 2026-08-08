package br.allandemiranda.fx.robot.controller.util;

import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.impl.CandlestickNotFoundException;
import br.allandemiranda.fx.robot.service.CandlestickService;
import br.allandemiranda.fx.robot.service.SymbolService;
import java.time.OffsetDateTime;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@UtilityClass
public class CandlestickUtils {

  private static Logger log() {
    return LoggerFactory.getLogger(CandlestickUtils.class);
  }

  public static Mono<CandlestickDto> getCandlestick(String symbolName, Timeframe timeframe, OffsetDateTime timestamp, SymbolService symbolService, CandlestickService candlestickService) {
    CandlestickUtils.log().trace("getCandlestick(symbolName={}, timeframe={}, timestamp={})", symbolName, timeframe, timestamp);
    return SymbolUtils.getSymbol(symbolName, symbolService).flatMap(symbolDto -> candlestickService.get(symbolDto, timeframe, timestamp))
        .switchIfEmpty(Mono.error(() -> new CandlestickNotFoundException(symbolName, timeframe, timestamp)));
  }
}

package br.allandemiranda.fx.robot.controller.util;

import br.allandemiranda.fx.robot.dto.TickDto;
import br.allandemiranda.fx.robot.exception.impl.TickNotFoundException;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.TickService;
import java.time.OffsetDateTime;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@UtilityClass
public class TickUtils {

  private static Logger log() {
    return LoggerFactory.getLogger(TickUtils.class);
  }

  public static Mono<TickDto> getTick(String symbolName, OffsetDateTime timestamp, SymbolService symbolService, TickService tickService) {
    TickUtils.log().trace("getTick(symbolName={}, timestamp={})", symbolName, timestamp);
    return SymbolUtils.getSymbol(symbolName, symbolService).flatMap(symbolDto -> tickService.get(symbolDto, timestamp)).switchIfEmpty(Mono.error(() -> new TickNotFoundException(symbolName, timestamp)));
  }
}

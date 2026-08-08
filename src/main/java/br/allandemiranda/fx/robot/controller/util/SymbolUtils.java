package br.allandemiranda.fx.robot.controller.util;

import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
import br.allandemiranda.fx.robot.service.SymbolService;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@UtilityClass
public class SymbolUtils {

  private static Logger log() {
    return LoggerFactory.getLogger(SymbolUtils.class);
  }

  public static Mono<SymbolDto> getSymbol(String name, SymbolService symbolService) {
    SymbolUtils.log().trace("getSymbol(name={})", name);
    return symbolService.get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }
}

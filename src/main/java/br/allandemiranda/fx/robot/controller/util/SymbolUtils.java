package br.allandemiranda.fx.robot.controller.util;

import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
import br.allandemiranda.fx.robot.service.SymbolService;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@UtilityClass
public class SymbolUtils {

  public static Mono<SymbolDto> getSymbol(String name, SymbolService symbolService) {
    SymbolUtils.log.trace("getSymbol(name={})", name);
    return symbolService.get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }
}

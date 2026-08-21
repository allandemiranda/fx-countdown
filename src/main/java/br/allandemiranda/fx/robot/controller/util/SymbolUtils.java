package br.allandemiranda.fx.robot.controller.util;

import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@UtilityClass
public class SymbolUtils {

  public static Mono<SymbolDto> getSymbol(String name, SymbolService symbolService) {
    log.trace("getSymbol(name={})", name);
    return symbolService.get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }
}

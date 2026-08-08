package br.allandemiranda.fx.robot.controller.util;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.impl.ExpertAdvisorNotFoundException;
import br.allandemiranda.fx.robot.service.ExpertAdvisorService;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@UtilityClass
public class ExpertAdvisorUtils {

  public static Mono<ExpertAdvisorDto> getExpertAdvisor(SymbolDto symbolDto, Timeframe timeframe, String name, ExpertAdvisorService expertAdvisorService) {
    ExpertAdvisorUtils.log.trace("getExpertAdvisor(symbolDto={}, timeframe={}, name={})", symbolDto, timeframe, name);
    return expertAdvisorService.get(symbolDto, timeframe, name).switchIfEmpty(Mono.error(() -> new ExpertAdvisorNotFoundException(name)));
  }
}

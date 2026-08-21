package br.allandemiranda.fx.robot.controller.util;

import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.impl.ExpertAdvisorNotFoundException;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import br.allandemiranda.fx.robot.service.input.InputService;
import br.allandemiranda.fx.robot.service.utils.IndicatorUtils;
import br.allandemiranda.fx.robot.service.utils.InputUtils;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@UtilityClass
public class ExpertAdvisorUtils {

  public static Mono<Void> deleteEaAndAllInputAndAllIndicator(String symbolName, Timeframe timeframe, String eaName, SymbolService symbolService, ExpertAdvisorService expertAdvisorService,
      List<IndicatorService<?, ?, ?>> indicatorServices, List<InputService<?, ?, ?>> inputServices) {
    log.trace("deleteEaAndAllInputAndAllIndicator(symbolName={}, timeframe={}, eaName={})", symbolName, timeframe, eaName);
    return SymbolUtils.getSymbol(symbolName, symbolService)
        .flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, eaName, expertAdvisorService))
        .flatMap(expertAdvisorDto -> {
          Collection<Mono<?>> indicatorDeletions = IndicatorUtils.doActionForAllParallel(indicatorServices, indicatorService -> indicatorService.delete(expertAdvisorDto));
          Collection<Mono<?>> inputDeletions = InputUtils.doActionForAllParallel(inputServices, inputService -> inputService.delete(expertAdvisorDto));
          return Flux.fromStream(Stream.concat(indicatorDeletions.stream(), inputDeletions.stream())).then(expertAdvisorService.delete(expertAdvisorDto));
        })
        .doOnError(throwable -> log.warn("Trouble deleting EA [eaName={}]", eaName, throwable));
  }

  public static Mono<ExpertAdvisorDto> getExpertAdvisor(SymbolDto symbolDto, Timeframe timeframe, String eaName, ExpertAdvisorService expertAdvisorService) {
    log.trace("getExpertAdvisor(symbolDto={}, timeframe={}, eaName={})", symbolDto, timeframe, eaName);
    return expertAdvisorService.get(symbolDto.symbolName(), timeframe, eaName).switchIfEmpty(Mono.error(() -> new ExpertAdvisorNotFoundException(eaName)));
  }
}

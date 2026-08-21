package br.allandemiranda.fx.robot.controller.indicator;

import br.allandemiranda.fx.robot.annotation.field.EaName;
import br.allandemiranda.fx.robot.annotation.field.SymbolName;
import br.allandemiranda.fx.robot.controller.util.ExpertAdvisorUtils;
import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.indicator.create.IndicatorCreate;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.indicator.provider.Indicator;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NullMarked
@Validated
public interface IndicatorController<M extends Indicator, D extends Indicator, C extends IndicatorCreate> {

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  default Mono<D> create(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable("expertAdvisorName") @EaName String expertAdvisorName,
      @RequestBody @Valid C indicatorCreateDto) {
    this.log().debug("Create [symbolName={}, timeframe={}, expertAdvisorName={}, indicatorCreateDto={}]", symbolName, timeframe, expertAdvisorName, indicatorCreateDto);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, expertAdvisorName, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorNameDto -> this.getService().create(expertAdvisorNameDto, indicatorCreateDto))
        .doOnError(throwable -> this.log().warn("Trouble for creating indicator [expertAdvisorName={}, indicatorCreateDto={}]", expertAdvisorName, indicatorCreateDto, throwable)).switchIfEmpty(Mono.defer(() -> {
          this.log().warn("Error creating indicator: create returned empty indicator [expertAdvisorName={}, indicatorCreateDto={}]", expertAdvisorName, indicatorCreateDto);
          return Mono.error(IllegalStateException::new);
        }));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  default Flux<D> findAll(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable("expertAdvisorName") @EaName String expertAdvisorName) {
    this.log().debug("Find All [symbolName={}, timeframe={}, expertAdvisorName={}]", symbolName, timeframe, expertAdvisorName);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, expertAdvisorName, this.getExpertAdvisorService()))
        .flatMapMany(expertAdvisorNameDto -> this.getService().get(expertAdvisorNameDto))
        .doOnError(throwable -> this.log().warn("Trouble for finding all indicators [expertAdvisorName={}]", expertAdvisorName, throwable));
  }

  @Contract(pure = true)
  ExpertAdvisorService getExpertAdvisorService();

  @Contract(pure = true)
  IndicatorService<M, D, C> getService();

  @Contract(pure = true)
  SymbolService getSymbolService();

}

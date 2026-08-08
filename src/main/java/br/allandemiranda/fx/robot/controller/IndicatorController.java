package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.util.ExpertAdvisorUtils;
import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.IndicatorCreateDto;
import br.allandemiranda.fx.robot.dto.IndicatorDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.IndicatorModel;
import br.allandemiranda.fx.robot.service.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.IndicatorService;
import br.allandemiranda.fx.robot.service.SymbolService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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

@Validated
public interface IndicatorController<M extends IndicatorModel, D extends IndicatorDto, C extends IndicatorCreateDto> {

  IndicatorService<M, D, C> getService();

  ExpertAdvisorService getExpertAdvisorService();

  SymbolService getSymbolService();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  default Flux<D> findAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable String expertAdvisorName) {
    this.log().debug("Find All [symbolName={}, timeframe={}, expertAdvisorName={}]", symbolName, timeframe, expertAdvisorName);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, expertAdvisorName, this.getExpertAdvisorService()))
        .flatMapMany(expertAdvisorNameDto -> this.getService().get(expertAdvisorNameDto))
        .doOnError(throwable -> this.log().warn("Trouble for finding all indicators [expertAdvisorName={}]", expertAdvisorName, throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  default Mono<D> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable String expertAdvisorName, @RequestBody @Valid C indicatorCreateDto) {
    this.log().debug("Create [symbolName={}, timeframe={}, expertAdvisorName={}, indicatorCreateDto={}]", symbolName, timeframe, expertAdvisorName, indicatorCreateDto);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, expertAdvisorName, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorNameDto -> this.getService().create(expertAdvisorNameDto, indicatorCreateDto))
        .doOnError(throwable -> log().warn("Trouble for creating indicator [expertAdvisorName={}, indicatorCreateDto={}]", expertAdvisorName, indicatorCreateDto, throwable)).switchIfEmpty(Mono.defer(() -> {
          log().warn("Error creating indicator: create returned empty indicator [expertAdvisorName={}, indicatorCreateDto={}]", expertAdvisorName, indicatorCreateDto);
          return Mono.error(IllegalStateException::new);
        }));
  }

}

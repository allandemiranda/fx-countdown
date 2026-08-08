package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.core.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.core.CandlestickDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.service.CandlestickService;
import br.allandemiranda.fx.robot.service.SymbolService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{symbolName}/candlesticks/{timeframe}")
public class CandlestickController {

  private final SymbolService symbolService;
  private final CandlestickService candlestickService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<CandlestickDto> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @RequestBody @Valid CandlestickCreateDto tickCreateDto) {
    log.debug("Create [symbolName={}, timeframe={}, tickCreateDto={}]", symbolName, timeframe, tickCreateDto);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> this.getCandlestickService().create(symbolDto, timeframe, tickCreateDto))
        .doOnError(throwable -> log.warn("Trouble for creating candlestick [symbolName={}, timeframe={}, tickCreateDto={}]", symbolName, timeframe, tickCreateDto, throwable))
        .switchIfEmpty(Mono.defer(() -> {
          log.warn("Error creating candlestick: create returned empty candlestick [symbolName={}, timeframe={}, tickCreateDto={}]", symbolName, timeframe, tickCreateDto);
          return Mono.error(IllegalStateException::new);
        }));
  }

}
package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.util.CandlestickUtils;
import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.service.CandlestickService;
import br.allandemiranda.fx.robot.service.SymbolService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/candlesticks/{timeframe}")
public class CandlestickController {

  private final SymbolService symbolService;
  private final CandlestickService candlestickService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  public Flux<CandlestickDto> findAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe timeframe) {
    log.debug("Find All [name={}, timeframe={}]", name, timeframe);
    return SymbolUtils.getSymbol(name, this.getSymbolService()).flatMapMany(symbolDto -> this.getCandlestickService().get(symbolDto, timeframe)).doOnError(throwable -> log.warn("Trouble for finding all candlesticks", throwable));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{timestamp}", produces = "application/json")
  public Mono<CandlestickDto> find(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe timeframe, @PathVariable @PastOrPresent @Valid OffsetDateTime timestamp) {
    log.debug("Find [name={}, timeframe={}, timestamp={}]", name, timeframe, timestamp);
    return CandlestickUtils.getCandlestick(name, timeframe, timestamp, this.getSymbolService(), this.getCandlestickService()).doOnError(throwable -> log.warn("Trouble for finding candlestick", throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public Mono<CandlestickDto> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe timeframe, @RequestBody @Valid CandlestickCreateDto tickCreateDto) {
    log.debug("Create [name={}, timeframe={}, tickCreateDto={}]", name, timeframe, tickCreateDto);
    return SymbolUtils.getSymbol(name, this.getSymbolService()).flatMap(symbolDto -> this.getCandlestickService().create(symbolDto, timeframe, tickCreateDto))
        .doOnError(throwable -> log.warn("Trouble for creating candlestick", throwable))
        .switchIfEmpty(Mono.defer(() -> {
          log.warn("Error creating candlestick: create returned empty candlestick");
          return Mono.error(IllegalStateException::new);
        }));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  public Mono<Void> delete(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe timeframe) {
    log.debug("Delete [name={}, timeframe={}]", name, timeframe);
    return SymbolUtils.getSymbol(name, this.getSymbolService()).flatMap(symbolDto -> this.getCandlestickService().delete(symbolDto, timeframe)).doOnError(throwable -> log.warn("Trouble for deleting tick", throwable));
  }


}
package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.core.SymbolCreateDto;
import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.service.CandlestickService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.TickService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("symbols")
public class SymbolController {

  private final SymbolService symbolService;
  private final TickService tickService;
  private final CandlestickService candlestickService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<SymbolDto> findAll() {
    log.debug("Find All");
    return this.getSymbolService().get();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<SymbolDto> find(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name) {
    log.debug("Find [name={}]", name);
    return SymbolUtils.getSymbol(name, this.getSymbolService()).doOnError(throwable -> log.warn("Trouble for finding symbol [name={}]", name, throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<SymbolDto> create(@RequestBody @Valid SymbolCreateDto symbolCreateDto) {
    log.debug("Create [symbolCreateDto={}]", symbolCreateDto);
    return this.getSymbolService().create(symbolCreateDto).doOnError(throwable -> log.warn("Trouble for creating symbol [symbolCreateDto={}]", symbolCreateDto, throwable)).switchIfEmpty(Mono.defer(() -> {
      log.warn("Error creating symbol: create returned empty symbol");
      return Mono.error(IllegalStateException::new);
    }));
  }

}

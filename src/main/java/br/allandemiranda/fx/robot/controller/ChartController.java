package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.create.ChartCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.impl.ChartNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
@RestController
@Validated
@RequestMapping("symbols/{name}")
public class ChartController {

  private final SymbolService symbolService;
  private final ChartService chartService;

  private Mono<SymbolDto> getSymbolDto(String name) {
    log.trace("getSymbolDto(name={})", name);
    return this.getSymbolService().get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }

  private Mono<ChartDto> getChartDto(String name, Timeframe period, SymbolDto symbolDto) {
    log.trace("getChartDto(name={}, period={}, symbolDto={})", name, period, symbolDto);
    return this.getChartService().get(symbolDto, period).switchIfEmpty(Mono.error(() -> new ChartNotFoundException(name, period)));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/charts", produces = "application/json")
  public Flux<ChartDto> findAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name) {
    log.debug("Find All [name={}]", name);
    return this.getSymbolDto(name).flatMapMany(symbolDto -> this.getChartService().get(symbolDto)).doOnError(throwable -> log.warn("Trouble for finding all chart", throwable));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/timeframes/{period}", produces = "application/json")
  public Mono<ChartDto> find(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    log.debug("Find [name={}, period={}]", name, period);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto)).doOnError(throwable -> log.warn("Trouble for finding chart", throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(path = "/charts", produces = "application/json")
  public Mono<ChartDto> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @RequestBody @Valid ChartCreateDto chartCreateDto) {
    log.debug("Create [name={}, chartCreateDto={}]", name, chartCreateDto);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartService().create(symbolDto, chartCreateDto)).doOnError(throwable -> log.warn("Trouble for creating chart", throwable)).switchIfEmpty(Mono.defer(() -> {
      log.warn("Error creating chart: create returned empty chart");
      return Mono.error(IllegalStateException::new);
    }));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(path = "/timeframes/{period}", produces = "application/json")
  public Mono<Void> delete(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    log.debug("Delete [name={}, period={}]", name, period);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto)).flatMap(chartDto -> this.getChartService().delete(chartDto)).doOnError(throwable -> log.warn("Trouble for deleting chart", throwable));
  }

}

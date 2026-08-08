package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.CreateChartObjectDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.impl.CandlestickNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.ChartObjectNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
import br.allandemiranda.fx.robot.model.ChartObjectModel;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
public interface ChartObjectController<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> extends EssentialServices {

  ChartObjectService<M, D, C> getService();

  String getChartObjectName();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  private Mono<ChartDto> getChartDto(String name, Timeframe period, SymbolDto symbolDto) {
    this.log().trace("getChartDto(name={}, timeframe={}, symbolDto={}", name, period, symbolDto);
    return this.getChartService().get(symbolDto, period).switchIfEmpty(Mono.error(() -> new CandlestickNotFoundException(name, period)));
  }

  private Mono<SymbolDto> getSymbolDto(String name) {
    this.log().trace("getSymbolDto(name={})", name);
    return this.getSymbolService().get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }

  private Mono<D> getChartObjectDto(String name, Timeframe period, OffsetDateTime timestamp, ChartDto chartDto) {
    this.log().trace("getChartObjectDto(name, timeframe={}, timestamp={}, chartDto={})", name, period, chartDto);
    return this.getService().get(chartDto, timestamp).switchIfEmpty(Mono.error(() -> new ChartObjectNotFoundException(name, period, this.getChartObjectName(), timestamp)));
  }

  default Mono<ChartDto> getChartDto(String name, Timeframe period) {
    this.log().trace("getChartDto(name={}, timeframe={})", name, period);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  default Flux<D> findAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    this.log().debug("Find All [name={}, timeframe={}]", name, period);
    return this.getChartDto(name, period).flatMapMany(chartDto -> this.getService().get(chartDto)).doOnError(throwable -> this.log().warn("Trouble for finding all chart objects", throwable));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{timestamp}", produces = "application/json")
  default Mono<D> find(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period, @PathVariable @PastOrPresent @Valid OffsetDateTime timestamp) {
    this.log().debug("Find [name={}, timeframe={}, timestamp={}]", name, period, timestamp);
    return this.getChartDto(name, period).flatMap(chartDto -> this.getChartObjectDto(name, period, timestamp, chartDto)).doOnError(throwable -> this.log().warn("Trouble for finding chart object", throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  default Mono<D> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period, @RequestBody @Valid C createChartObjectDto) {
    this.log().debug("Create [name={}, timeframe={}, createChartObjectDto={}]", name, period, createChartObjectDto);
    return this.getChartDto(name, period).flatMap(chartDto -> this.getService().create(chartDto, createChartObjectDto)).doOnError(throwable -> log().warn("Trouble for creating Chart Object", throwable)).switchIfEmpty(Mono.defer(() -> {
      log().warn("Error creating Chart Object: create returned empty Chart Object");
      return Mono.error(IllegalStateException::new);
    }));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  default Mono<Void> deleteAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    this.log().debug("Delete All [name={}, timeframe={}]", name, period);
    return this.getChartDto(name, period).flatMap(chartDto -> this.getService().delete(chartDto)).doOnError(throwable -> this.log().warn("Trouble for deleting all chart object", throwable));
  }

}

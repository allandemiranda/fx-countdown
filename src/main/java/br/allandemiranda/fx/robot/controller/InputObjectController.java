package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.InputObjectDto;
import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.SymbolDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.impl.ChartNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.InputObjectNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
import br.allandemiranda.fx.robot.model.InputObjectModel;
import br.allandemiranda.fx.robot.service.InputObjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
import reactor.core.publisher.Mono;

@Validated
public interface InputObjectController<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto> extends EssentialServices {

  InputObjectService<M, D, C> getService();

  String getInputObjectName();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  private Mono<ChartDto> getChartDto(String name, Timeframe period, SymbolDto symbolDto) {
    this.log().trace("getChartDto(name={}, period={}, symbolDto={}", name, period, symbolDto);
    return this.getChartService().get(symbolDto, period).switchIfEmpty(Mono.error(() -> new ChartNotFoundException(name, period)));
  }

  private Mono<SymbolDto> getSymbolDto(String name) {
    this.log().trace("getSymbolDto(name={})", name);
    return this.getSymbolService().get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }

  default Mono<D> getInputObjectDto(String name, Timeframe period, ChartDto chartDto) {
    this.log().trace("getInputObjectDto(name, period={}, chartDto={}", name, chartDto);
    return this.getService().get(chartDto).switchIfEmpty(Mono.error(() -> new InputObjectNotFoundException(name, period, this.getInputObjectName())));
  }

  default Mono<ChartDto> getChartDto(String name, Timeframe period) {
    this.log().trace("getChartDto(name={}, period={})", name, period);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  default Mono<D> find(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    this.log().debug("Find [name={}, period={}]", name, period);
    return this.getChartDto(name, period).flatMap(chartDto -> this.getInputObjectDto(name, period, chartDto)).doOnError(throwable -> this.log().warn("Trouble for finding input object", throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  default Mono<D> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period, @RequestBody @Valid C createInputObjectDto) {
    this.log().debug("Create [name={}, period={}, createInputObjectDto={}]", name, period, createInputObjectDto);
    return this.getChartDto(name, period).flatMap(chartDto -> this.getService().create(chartDto, createInputObjectDto)).doOnError(throwable -> log().warn("Trouble for creating Input Object", throwable)).switchIfEmpty(Mono.defer(() -> {
      log().warn("Error creating Input Object: create returned empty Input Object");
      return Mono.error(IllegalStateException::new);
    }));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  default Mono<Void> delete(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    this.log().debug("Delete [name={}, period={}]", name, period);
    return this.getChartDto(name, period).flatMap(chartDto -> this.getService().delete(chartDto)).doOnError(throwable -> this.log().warn("Trouble for deleting input object", throwable));
  }

}

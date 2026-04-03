package br.allandemiranda.fx.robot.controller.contract;

import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.ChartObjectNotFoundException;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
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
public interface ChartObjectController<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> {

  SymbolService getSymbolService();

  ChartService getChartService();

  ChartObjectService<M, D, C> getService();

  String getChartObjectName();

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  default Flux<D> findAll(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period) {
    return this.getSymbolService().getSymbol(name).flatMap(symbolDto -> this.getChartService().get(symbolDto, period)).flatMapMany(dto -> this.getService().get(dto));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{timestamp}", produces = "application/json")
  default Mono<D> find(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period, @PathVariable @NotNull @PastOrPresent @Valid OffsetDateTime timestamp) {
    return this.getSymbolService().getSymbol(name).flatMap(symbolDto -> this.getChartService().get(symbolDto, period)).flatMap(chartDto -> this.getService().get(chartDto, timestamp))
        .switchIfEmpty(Mono.error(() -> new ChartObjectNotFoundException(name, period, this.getChartObjectName(), timestamp)));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  default Mono<D> create(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period, @RequestBody @Valid C createChartObjectDto) {
    return this.getSymbolService().getSymbol(name).flatMap(symbolDto -> this.getChartService().get(symbolDto, period)).flatMap(chartDto -> this.getService().create(chartDto, createChartObjectDto));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  default Mono<Void> deleteAll(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period) {
    return this.getSymbolService().getSymbol(name).flatMap(symbolDto -> this.getChartService().get(symbolDto, period)).flatMap(chartDto -> this.getService().delete(chartDto));
  }

}

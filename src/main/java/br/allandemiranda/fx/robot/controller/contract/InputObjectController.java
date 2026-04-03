package br.allandemiranda.fx.robot.controller.contract;

import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.InputObjectNotFoundException;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public interface InputObjectController<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto> {

  SymbolService getSymbolService();

  ChartService getChartService();

  InputObjectService<M, D, C> getService();

  String getChartObjectName();

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{timestamp}", produces = "application/json")
  default Mono<D> find(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period) {
    return this.getSymbolService().getSymbol(name).flatMap(symbolDto -> this.getChartService().get(symbolDto, period)).flatMap(chartDto -> this.getService().get(chartDto))
        .switchIfEmpty(Mono.error(() -> new InputObjectNotFoundException(name, period, this.getChartObjectName())));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  default Mono<D> create(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period, @RequestBody @Valid C createInputObjectDto) {
    return this.getSymbolService().getSymbol(name).flatMap(symbolDto -> this.getChartService().get(symbolDto, period)).flatMap(chartDto -> this.getService().create(chartDto, createInputObjectDto));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  default Mono<Void> deleteAll(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period) {
    return this.getSymbolService().getSymbol(name).flatMap(symbolDto -> this.getChartService().get(symbolDto, period)).flatMap(chartDto -> this.getService().delete(chartDto));
  }

}

package br.allandemiranda.fx.robot.controller.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.impl.ChartNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.InputObjectNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
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

  private @NonNull Mono<ChartDto> getChartDto(String name, Timeframe period, SymbolDto symbolDto) {
    return this.getChartService().get(symbolDto, period).switchIfEmpty(Mono.error(() -> new ChartNotFoundException(name, period)));
  }

  private @NonNull Mono<SymbolDto> getSymbolDto(String name) {
    return this.getSymbolService().get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }

  private @NonNull Mono<D> getInputObjectDto(String name, Timeframe period, ChartDto chartDto) {
    return this.getService().get(chartDto).switchIfEmpty(Mono.error(() -> new InputObjectNotFoundException(name, period, this.getInputObjectName())));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  default Mono<D> find(@PathVariable String name, @PathVariable Timeframe period) {
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto)).flatMap(chartDto -> this.getInputObjectDto(name, period, chartDto));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  default Mono<D> create(@PathVariable String name, @PathVariable Timeframe period, @RequestBody @Valid C createInputObjectDto) {
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto)).flatMap(chartDto -> this.getService().create(chartDto, createInputObjectDto));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  default Mono<Void> deleteAll(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period) {
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto)).flatMap(chartDto -> this.getService().delete(chartDto));
  }

}

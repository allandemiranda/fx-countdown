package br.allandemiranda.fx.robot.controller.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.impl.ChartNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.ChartObjectNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import org.jspecify.annotations.NonNull;
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

  private @NonNull Mono<ChartDto> getChartDto(String name, Timeframe period, SymbolDto symbolDto) {
    return this.getChartService().get(symbolDto, period).switchIfEmpty(Mono.error(() -> new ChartNotFoundException(name, period)));
  }

  private @NonNull Mono<SymbolDto> getSymbolDto(String name) {
    return this.getSymbolService().get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }

  private @NonNull Mono<D> getChartObjectDto(String name, Timeframe period, OffsetDateTime timestamp, ChartDto chartDto) {
    return this.getService().get(chartDto, timestamp).switchIfEmpty(Mono.error(() -> new ChartObjectNotFoundException(name, period, this.getChartObjectName(), timestamp)));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  default Flux<D> findAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto)).flatMapMany(dto -> this.getService().get(dto));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{timestamp}", produces = "application/json")
  default Mono<D> find(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period, @PathVariable @PastOrPresent @Valid OffsetDateTime timestamp) {
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto)).flatMap(chartDto -> this.getChartObjectDto(name, period, timestamp, chartDto));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  default Mono<D> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period, @RequestBody @Valid C createChartObjectDto) {
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto)).flatMap(chartDto -> this.getService().create(chartDto, createChartObjectDto)).switchIfEmpty(Mono.error(IllegalStateException::new));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  default Mono<Void> deleteAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getChartDto(name, period, symbolDto)).flatMap(chartDto -> this.getService().delete(chartDto));
  }

}

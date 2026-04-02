package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.ChartCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.ChartNotFoundException;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@RestController
@Validated
@RequestMapping("symbols/{name}")
public final class ChartController {

  private final SymbolService symbolService;
  private final ChartService chartService;

  private Mono<SymbolDto> getSymbol(String name) {
    return this.getSymbolService().getSymbol(name);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/charts", produces = "application/json")
  public Flux<ChartDto> findAll(@PathVariable @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank @Valid String name) {
    return this.getSymbol(name).flatMapMany(symbolDto -> this.getChartService().get(symbolDto));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/timeframes/{period}", produces = "application/json")
  public Mono<ChartDto> find(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period) {
    return this.getSymbol(name).flatMap(symbolDto -> this.getChartService().get(symbolDto, period)).switchIfEmpty(Mono.error(() -> new ChartNotFoundException(name, period)));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(path = "/charts", produces = "application/json")
  public Mono<ChartDto> create(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @RequestBody @Valid ChartCreateDto chartCreateDto) {
    return this.getSymbol(name).flatMap(symbolDto -> this.getChartService().create(symbolDto, chartCreateDto));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(path = "/timeframes/{period}", produces = "application/json")
  public Mono<Void> deleteAll(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period) {
    return this.getSymbol(name).flatMap(symbolDto -> this.getChartService().delete(symbolDto, period));
  }

}

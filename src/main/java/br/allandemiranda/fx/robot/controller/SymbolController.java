package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.exception.SymbolNotFoundException;
import br.allandemiranda.fx.robot.service.SymbolService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
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
@Getter
@Validated
@RestController
@RequestMapping("symbols")
public final class SymbolController {

  private final SymbolService symbolService;

  private @NonNull Mono<SymbolDto> getSymbolDto(String name) {
    return this.getSymbolService().getSymbol(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  public Flux<SymbolDto> findAll() {
    return this.getSymbolService().getSymbols();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{name}", produces = "application/json")
  public Mono<SymbolDto> find(@PathVariable @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank @Valid String name) {
    return this.getSymbolDto(name);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public Mono<SymbolDto> create(@RequestBody @Valid SymbolCreateDto symbolCreateDto) {
    return this.getSymbolService().createSymbol(symbolCreateDto);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(path = "/{name}", produces = "application/json")
  public Mono<Void> deleteAll(@PathVariable @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank @Valid String name) {
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getSymbolService().deleteSymbol(symbolDto.name()));
  }

}

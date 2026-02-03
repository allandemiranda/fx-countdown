package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.impl.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
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
@Validated
@RestController
@RequestMapping("symbols")
public class SymbolController {

  private final SymbolService symbolService;

  private Mono<SymbolDto> getSymbolDto(String name) {
    log.trace("getSymbolDto(name={})", name);
    return this.getSymbolService().get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  public Flux<SymbolDto> findAll() {
    log.debug("Find All");
    return this.getSymbolService().get();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{name}", produces = "application/json")
  public Mono<SymbolDto> find(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name) {
    log.debug("Find [name={}]", name);
    return this.getSymbolDto(name).doOnError(throwable -> log.warn("Trouble for finding symbol", throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public Mono<SymbolDto> create(@RequestBody @Valid SymbolCreateDto symbolCreateDto) {
    log.debug("Create [symbolCreateDto={}]", symbolCreateDto);
    return this.getSymbolService().create(symbolCreateDto).doOnError(throwable -> log.warn("Trouble for creating symbol", throwable)).switchIfEmpty(Mono.defer(() -> {
      log.warn("Error creating symbol: create returned empty symbol");
      return Mono.error(IllegalStateException::new);
    }));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(path = "/{name}", produces = "application/json")
  public Mono<Void> delete(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name) {
    log.debug("Delete [name={}]", name);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getSymbolService().delete(symbolDto.name())).doOnError(throwable -> log.warn("Trouble for deleting symbol", throwable));
  }

}

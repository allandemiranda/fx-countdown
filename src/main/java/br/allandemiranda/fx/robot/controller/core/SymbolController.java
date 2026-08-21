package br.allandemiranda.fx.robot.controller.core;

import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.dto.core.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols")
public class SymbolController {

  private final SymbolService symbolService;

  /**
   * Registers a new financial symbol or updates an existing one with point and swap values.
   *
   * @param symbolCreateDto the symbol configuration payload
   * @return a {@link Mono} emitting the registered {@link SymbolDto}
   */
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<SymbolDto> create(@RequestBody @Valid SymbolCreateDto symbolCreateDto) {
    log.debug("Create an Symbol [symbolCreateDto={}]", symbolCreateDto);
    return this.getSymbolService().create(symbolCreateDto).doOnError(throwable -> log.warn("Trouble for creating symbol [symbolCreateDto={}]", symbolCreateDto, throwable)).switchIfEmpty(Mono.defer(() -> {
      log.warn("Error creating symbol: create returned empty symbol");
      return Mono.error(IllegalStateException::new);
    }));
  }

  /**
   * Lists all registered financial symbols in the system.
   *
   * @return a {@link Flux} of {@link SymbolDto}
   */
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<SymbolDto> findAll() {
    log.debug("Find All Symbols");
    return this.getSymbolService().get();
  }

}

package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.base.TickDto;
import br.allandemiranda.fx.robot.dto.impl.create.TickCreateDto;
import br.allandemiranda.fx.robot.exception.impl.SymbolNotFoundException;
import br.allandemiranda.fx.robot.exception.impl.TickNotFoundException;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.TickService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.OffsetDateTime;
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
@RequestMapping("symbols/{name}/ticks")
public class TickController {

  private final SymbolService symbolService;
  private final TickService tickService;

  private Mono<SymbolDto> getSymbolDto(String name) {
    log.trace("getSymbolDto(name={})", name);
    return this.getSymbolService().get(name).switchIfEmpty(Mono.error(() -> new SymbolNotFoundException(name)));
  }

  private Mono<TickDto> getTickDto(SymbolDto symbolDto, OffsetDateTime timestamp) {
    log.trace("getTickDto(symbolDto={}, timestamp={})", symbolDto, timestamp);
    return this.getTickService().get(symbolDto, timestamp).switchIfEmpty(Mono.error(() -> new TickNotFoundException(symbolDto.name(), timestamp)));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  public Flux<TickDto> findAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name) {
    log.debug("Find All [name={}]", name);
    return this.getSymbolDto(name).flatMapMany(symbolDto -> this.getTickService().get(symbolDto)).doOnError(throwable -> log.warn("Trouble for finding all tick", throwable));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{timestamp}", produces = "application/json")
  public Mono<TickDto> find(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @PastOrPresent @Valid OffsetDateTime timestamp) {
    log.debug("Find [name={}, timestamp={}]", name, timestamp);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getTickDto(symbolDto, timestamp)).doOnError(throwable -> log.warn("Trouble for finding tick", throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public Mono<TickDto> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @RequestBody @Valid TickCreateDto tickCreateDto) {
    log.debug("Create [name={}, tickCreateDto={}]", name, tickCreateDto);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getTickService().create(symbolDto, tickCreateDto)).doOnError(throwable -> log.warn("Trouble for creating tick", throwable)).switchIfEmpty(Mono.defer(() -> {
      log.warn("Error creating tick: create returned empty tick");
      return Mono.error(IllegalStateException::new);
    }));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(path = "/{timestamp}", produces = "application/json")
  public Mono<Void> delete(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @PastOrPresent @Valid OffsetDateTime timestamp) {
    log.debug("Delete [name={}, timestamp={}]", name, timestamp);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getTickDto(symbolDto, timestamp)).flatMap(tickDto -> this.getTickService().delete(tickDto)).doOnError(throwable -> log.warn("Trouble for deleting tick", throwable));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  public Mono<Void> deleteAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name) {
    log.debug("Delete All [name={}]", name);
    return this.getSymbolDto(name).flatMap(symbolDto -> this.getTickService().deleteAll(symbolDto)).doOnError(throwable -> log.warn("Trouble for deleting all tick", throwable));
  }

}

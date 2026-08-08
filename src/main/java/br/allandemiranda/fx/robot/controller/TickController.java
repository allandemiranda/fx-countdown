package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.TickCreateDto;
import br.allandemiranda.fx.robot.dto.TickDto;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.TickService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public Mono<TickDto> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @RequestBody @Valid TickCreateDto tickCreateDto) {
    log.debug("Create [name={}, tickCreateDto={}]", name, tickCreateDto);
    return SymbolUtils.getSymbol(name, this.getSymbolService()).flatMap(symbolDto -> this.getTickService().create(symbolDto, tickCreateDto)).doOnError(throwable -> log.warn("Trouble for creating tick", throwable))
        .switchIfEmpty(Mono.defer(() -> {
          log.warn("Error creating tick: create returned empty tick");
          return Mono.error(IllegalStateException::new);
        }));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  public Mono<Void> delete(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name) {
    log.debug("Delete [name={}]", name);
    return SymbolUtils.getSymbol(name, this.getSymbolService()).flatMap(symbolDto -> this.getTickService().delete(symbolDto)).doOnError(throwable -> log.warn("Trouble for deleting tick", throwable));
  }

}

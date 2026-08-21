package br.allandemiranda.fx.robot.controller.core;

import br.allandemiranda.fx.robot.annotation.field.SymbolName;
import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.core.TickDto;
import br.allandemiranda.fx.robot.dto.core.create.TickCreateDto;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.core.TickService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{symbolName}/ticks")
public class TickController {

  private final SymbolService symbolService;
  private final TickService tickService;

  /**
   * Ingests a new tick quote or updates an existing tick at the given timestamp.
   *
   * @param symbolName    the 6-letter asset ticker (e.g., EURUSD)
   * @param tickCreateDto the tick payload containing ask, bid, and timestamp
   * @return a {@link Mono} emitting the saved {@link TickDto}
   */
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<TickDto> create(@PathVariable @SymbolName @Valid String symbolName, @RequestBody @Valid TickCreateDto tickCreateDto) {
    log.debug("Create an Tick [symbolName={}, tickCreateDto={}]", symbolName, tickCreateDto);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> this.getTickService().create(symbolDto.symbolName(), tickCreateDto))
        .doOnError(throwable -> log.warn("Trouble for creating an tick [symbolName={}, tickCreateDto={}]", symbolName, tickCreateDto, throwable))
        .switchIfEmpty(Mono.defer(() -> {
          log.warn("Error creating an tick: create returned empty tick");
          return Mono.error(IllegalStateException::new);
        }));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<TickDto> getLastTick(@PathVariable @SymbolName @Valid String symbolName) {
    log.debug("Get Last Tick [symbolName={}]", symbolName);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> this.getTickService().getLastTick(symbolDto.symbolName()));
  }

}

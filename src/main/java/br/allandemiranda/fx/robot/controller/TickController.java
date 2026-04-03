package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.base.TickDto;
import br.allandemiranda.fx.robot.dto.create.TickCreateDto;
import br.allandemiranda.fx.robot.exception.TickNotFoundException;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.TickService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
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
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/ticks")
public class TickController {

  private final SymbolService symbolService;
  private final TickService tickService;

  private Mono<SymbolDto> getSymbol(String name) {
    return this.getSymbolService().getSymbol(name);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  public Flux<TickDto> findAll(@PathVariable @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank @Valid String name) {
    return this.getSymbol(name).flatMapMany(symbolDto -> this.getTickService().getTick(symbolDto));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{timestamp}", produces = "application/json")
  public Mono<TickDto> find(@PathVariable @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @PastOrPresent @Valid OffsetDateTime timestamp) {
    return this.getSymbol(name).flatMap(symbolDto -> this.getTickService().getTick(symbolDto, timestamp)).switchIfEmpty(Mono.error(() -> new TickNotFoundException(name, timestamp)));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public Mono<TickDto> create(@PathVariable @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank @Valid String name, @RequestBody @Valid TickCreateDto tickCreateDto) {
    return this.getSymbol(name).flatMap(symbolDto -> this.getTickService().createTick(symbolDto, tickCreateDto));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(produces = "application/json")
  public Mono<Void> deleteAll(@PathVariable @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank @Valid String name) {
    return this.getSymbol(name).flatMap(symbolDto -> this.getTickService().deleteTick(symbolDto));
  }

}

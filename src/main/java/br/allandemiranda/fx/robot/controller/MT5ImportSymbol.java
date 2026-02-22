package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.SymbolCreateDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.exception.SymbolNotFoundException;
import br.allandemiranda.fx.robot.service.SymbolService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@RestController
@RequestMapping("mt5/import/symbols")
public class MT5ImportSymbol {

  private final SymbolService symbolService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  public Collection<SymbolDto> getSymbols() {
    return this.getSymbolService().getSymbols();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{name}", produces = "application/json")
  public SymbolDto getSymbol(@PathVariable @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank @Valid String name) {
    return this.getSymbolService().getSymbol(name).orElseThrow(SymbolNotFoundException::new);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public SymbolDto createSymbol(@RequestBody @Valid SymbolCreateDto symbolCreateDto) {
    return this.getSymbolService().create(symbolCreateDto);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(path = "/{name}", produces = "application/json")
  public void deleteSymbol(@PathVariable @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank @Valid String name) {
    SymbolDto symbolDto = this.getSymbolService().getSymbol(name).orElseThrow(SymbolNotFoundException::new);
    this.getSymbolService().deleteSymbol(symbolDto);
  }
}

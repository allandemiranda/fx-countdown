package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.mapper.SymbolMapper;
import br.allandemiranda.fx.robot.model.Symbol;
import br.allandemiranda.fx.robot.repository.SymbolRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Service
public final class SymbolService {

  private final SymbolRepository repository;
  private final SymbolMapper mapper;

  public Mono<SymbolDto> getSymbol(String name) {
    return this.getRepository().findById(name).map(symbol -> this.getMapper().toDto(symbol));
  }

  public Flux<SymbolDto> getSymbols() {
    return this.getRepository().findAll().map(symbol -> this.getMapper().toDto(symbol));
  }

  public Mono<SymbolDto> createSymbol(SymbolCreateDto symbolCreateDto) {
    Symbol model = this.getMapper().toModel(symbolCreateDto);
    Mono<Symbol> savedSymbol = this.getRepository().save(model);
    return savedSymbol.map(symbol -> this.getMapper().toDto(symbol));
  }

  public Mono<Void> deleteSymbol(String name) {
    return this.getRepository().deleteById(name);
  }

}

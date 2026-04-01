package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.SymbolCreateDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.mapper.SymbolMapper;
import br.allandemiranda.fx.robot.model.Symbol;
import br.allandemiranda.fx.robot.repository.SymbolRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class SymbolService {

  private final SymbolRepository repository;

  @Transactional(readOnly = true)
  public Mono<SymbolDto> getSymbol(String name) {
    return this.getRepository().findById(name).map(SymbolMapper::toSymbolDto);
  }

  @Transactional(readOnly = true)
  public Flux<SymbolDto> getSymbols() {
    return this.getRepository().findAll().map(SymbolMapper::toSymbolDto);
  }

  public Mono<SymbolDto> createSymbol(SymbolCreateDto symbolCreateDto) {
    Symbol model = SymbolMapper.toSymbol(symbolCreateDto);
    Mono<Symbol> savedSymbol = this.getRepository().save(model);
    return savedSymbol.map(SymbolMapper::toSymbolDto);
  }

  public Mono<Void> deleteSymbol(String name) {
    return this.getRepository().deleteById(name);
  }

}

package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.dto.SymbolCreateDto;
import br.allandemiranda.fx.robot.mapper.SymbolMapper;
import br.allandemiranda.fx.robot.model.Symbol;
import br.allandemiranda.fx.robot.repository.SymbolRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Getter
@Service
public class SymbolService {

  private final SymbolRepository repository;
  private final SymbolMapper mapper;

  public Mono<SymbolDto> get(String name) {
    log.debug("Get [name={}]", name);
    return this.getRepository().findById(name).map(symbol -> this.getMapper().toDto(symbol));
  }

  public Flux<SymbolDto> get() {
    log.debug("Get");
    return this.getRepository().findAll().map(symbol -> this.getMapper().toDto(symbol));
  }

  public Mono<SymbolDto> create(SymbolCreateDto symbolCreateDto) {
    log.debug("Create [symbolCreateDto={}]", symbolCreateDto);
    Symbol model = this.getMapper().toModel(symbolCreateDto);
    log.trace("Create [symbolCreateDto={}], new object generated to save [symbol={}]", symbolCreateDto, model);
    Mono<Symbol> savedSymbol = this.getRepository().save(model);
    return savedSymbol.map(symbol -> this.getMapper().toDto(symbol));
  }

  public Mono<Void> delete(String name) {
    log.debug("Delete [name={}]", name);
    return this.getRepository().deleteById(name);
    //TODO: implement the delete in cascade for Candlesticks and Ticks that use the same Symbol
  }

}

package br.allandemiranda.fx.robot.service.core;

import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.dto.core.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.mapper.core.SymbolMapper;
import br.allandemiranda.fx.robot.model.core.impl.SymbolEntity;
import br.allandemiranda.fx.robot.repository.core.SymbolRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service for managing financial symbols and asset metadata.
 */
@NullMarked
@Slf4j
@AllArgsConstructor
@Getter
@Service
public class SymbolService {

  private final SymbolMapper mapper;
  private final SymbolRepository repository;

  /**
   * Registers a new symbol or updates an existing symbol record.
   *
   * @param symbolCreateDto the symbol configuration payload
   * @return a {@link Mono} emitting the persisted {@link SymbolDto}
   */
  public Mono<SymbolDto> create(SymbolCreateDto symbolCreateDto) {
    log.debug("Create Symbol: {}", symbolCreateDto);
    SymbolEntity model = this.getMapper().toModel(symbolCreateDto);
    return this.get(symbolCreateDto.symbolName()).flatMap(symbolDto -> {
      log.debug("Symbol to create already exist [symbolDto={}], we will update the symbol to [symbolDto={}]", symbolDto, model);
      return this.getRepository().save(model).map(symbol -> this.getMapper().toDto(symbol));
    }).switchIfEmpty(this.getRepository().save(model).map(symbol -> this.getMapper().toDto(symbol)));
  }

  /**
   * Retrieves all registered financial symbols in the database.
   *
   * @return a {@link Flux} of {@link SymbolDto}
   */
  public Flux<SymbolDto> get() {
    log.debug("Get all Symbols");
    return this.getRepository().findAll().map(symbol -> this.getMapper().toDto(symbol));
  }

  /**
   * Retrieves a specific symbol by its 6-character uppercase ticker eaName.
   *
   * @param name the symbol eaName (e.g., EURUSD)
   * @return a {@link Mono} emitting the {@link SymbolDto} or empty if not found
   */
  public Mono<SymbolDto> get(String name) {
    log.debug("Get an Symbol by [name={}]", name);
    return this.getRepository().findById(name).map(symbol -> this.getMapper().toDto(symbol));
  }

}

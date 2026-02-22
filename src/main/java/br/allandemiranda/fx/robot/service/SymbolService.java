package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.SymbolCreateDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.exception.SymbolNotFoundException;
import br.allandemiranda.fx.robot.mapper.SymbolCreateMapper;
import br.allandemiranda.fx.robot.mapper.SymbolMapper;
import br.allandemiranda.fx.robot.model.Symbol;
import br.allandemiranda.fx.robot.repository.SymbolRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class SymbolService {

  private final SymbolRepository repository;
  private final SymbolMapper mapper;
  private final SymbolCreateMapper createMapper;

  public SymbolDto create(SymbolCreateDto symbolCreateDto) {
    Symbol entity = this.getCreateMapper().toEntity(symbolCreateDto);
    Symbol symbol = this.getRepository().save(entity);
    return this.getMapper().toDto(symbol);
  }

  @Transactional(readOnly = true)
  public Optional<SymbolDto> getSymbol(String name) {
    return this.getSymbolEntity(name).map(symbol -> this.getMapper().toDto(symbol));
  }

  @Transactional(readOnly = true)
  public Collection<SymbolDto> getSymbols() {
    return this.getRepository().findAll().stream().map(symbol -> this.getMapper().toDto(symbol)).collect(Collectors.toList());
  }

  private Optional<Symbol> getSymbolEntity(String name) {
    return this.getRepository().findFirstByName(name);
  }

  public void deleteSymbol(@NonNull SymbolDto symbolDto) {
    this.getRepository().deleteById(symbolDto.getName());
  }

}

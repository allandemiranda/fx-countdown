package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.SymbolCreateDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
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

  private final SymbolRepository symbolRepository;
  private final SymbolMapper symbolMapper;
  private final SymbolCreateMapper symbolCreateMapper;

  public SymbolDto create(SymbolCreateDto symbolCreateDto) {
    Symbol entity = this.getSymbolCreateMapper().toEntity(symbolCreateDto);
    Symbol symbol = this.getSymbolRepository().save(entity);
    return this.getSymbolMapper().toDto(symbol);
  }

  @Transactional(readOnly = true)
  public Optional<SymbolDto> getSymbol(String name) {
    return this.getSymbolEntity(name).map(symbol -> this.getSymbolMapper().toDto(symbol));
  }

  @Transactional(readOnly = true)
  public Collection<SymbolDto> getSymbols() {
    return this.getSymbolRepository().findAll().stream().map(symbol -> this.getSymbolMapper().toDto(symbol)).collect(Collectors.toList());
  }

  private Optional<Symbol> getSymbolEntity(String name) {
    return this.getSymbolRepository().findFirstByName(name);
  }

  public void deleteSymbol(@NonNull SymbolDto symbolDto) {
    this.getSymbolRepository().deleteById(symbolDto.getName());
  }

}

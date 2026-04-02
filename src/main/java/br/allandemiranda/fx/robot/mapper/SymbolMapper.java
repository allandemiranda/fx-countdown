package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.model.Symbol;
import org.springframework.stereotype.Component;

@Component
public final class SymbolMapper {

  public SymbolDto toDto(Symbol symbol) {
    return new SymbolDto(symbol.name(), symbol.point(), symbol.swapLong(), symbol.swapShort());
  }

  public Symbol toModel(SymbolCreateDto symbolCreateDto) {
    return new Symbol(symbolCreateDto.name(), symbolCreateDto.point(), symbolCreateDto.swapLong(), symbolCreateDto.swapShort());
  }

}

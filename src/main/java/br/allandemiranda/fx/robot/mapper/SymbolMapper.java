package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.SymbolCreateDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.model.Symbol;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class SymbolMapper {

  public static @NonNull SymbolDto toSymbolDto(@NonNull Symbol symbol) {
    return new SymbolDto(symbol.name(), symbol.point(), symbol.swapLong(), symbol.swapShort());
  }

  public static @NonNull Symbol toSymbol(@NonNull SymbolCreateDto symbolCreateDto) {
    return new Symbol(symbolCreateDto.name(), symbolCreateDto.point(), symbolCreateDto.swapLong(), symbolCreateDto.swapShort());
  }

}

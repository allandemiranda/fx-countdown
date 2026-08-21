package br.allandemiranda.fx.robot.mapper.core;

import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.dto.core.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.model.core.impl.SymbolEntity;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Component;

@NullMarked
@Component
public final class SymbolMapper {

  @Contract("_ -> new")
  public SymbolDto toDto(SymbolEntity symbolEntity) {
    return new SymbolDto(symbolEntity.symbolName(), symbolEntity.point(), symbolEntity.swapLong(), symbolEntity.swapShort());
  }

  @Contract("_ -> new")
  public SymbolEntity toModel(SymbolCreateDto symbolCreateDto) {
    return new SymbolEntity(symbolCreateDto.symbolName(), symbolCreateDto.point(), symbolCreateDto.swapLong(), symbolCreateDto.swapShort());
  }

}

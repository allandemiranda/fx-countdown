package br.allandemiranda.fx.robot.mapper.core;

import br.allandemiranda.fx.robot.dto.core.TickDto;
import br.allandemiranda.fx.robot.dto.core.create.TickCreateDto;
import br.allandemiranda.fx.robot.model.core.impl.TickEntity;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Component;

@NullMarked
@Component
public final class TickMapper {

  @Contract("_ -> new")
  public TickDto toDto(TickEntity tickEntity) {
    return new TickDto(tickEntity.id(), tickEntity.symbolName(), tickEntity.timestamp(), tickEntity.ask(), tickEntity.bid());
  }

  @Contract("_, _, _ -> new")
  public TickEntity toModel(UUID id, String symbolName, TickCreateDto tickCreateDto) {
    return new TickEntity(id, symbolName, tickCreateDto.timestamp(), tickCreateDto.ask(), tickCreateDto.bid());
  }

}

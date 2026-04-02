package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.TickCreateDto;
import br.allandemiranda.fx.robot.dto.base.TickDto;
import br.allandemiranda.fx.robot.model.Tick;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class TickMapper {

  public TickDto toDto(SymbolDto symbolDto, Tick tick) {
    return new TickDto(tick.id(), symbolDto, tick.timestamp(), tick.ask(), tick.bid());
  }

  public Tick toModel(UUID id, SymbolDto symbolDto, TickCreateDto tickCreateDto) {
    return new Tick(id, symbolDto.name(), tickCreateDto.timestamp(), tickCreateDto.ask(), tickCreateDto.bid());
  }

}

package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.TickCreateDto;
import br.allandemiranda.fx.robot.dto.TickDto;
import br.allandemiranda.fx.robot.model.Tick;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class TickMapper {

  public static @NonNull TickDto toTickDto(@NonNull ChartDto chartDto, @NonNull Tick tick) {
    return new TickDto(tick.id(), chartDto, tick.timestamp(), tick.ask(), tick.bid());
  }

  public static @NonNull Tick toTick(UUID id, @NonNull ChartDto chartDto, @NonNull TickCreateDto tickCreateDto) {
    return new Tick(id, chartDto.id(), tickCreateDto.timestamp(), tickCreateDto.ask(), tickCreateDto.bid());
  }

}

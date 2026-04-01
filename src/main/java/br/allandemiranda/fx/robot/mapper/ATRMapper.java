package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ATRCreateDto;
import br.allandemiranda.fx.robot.dto.ATRDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.model.ATR;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ATRMapper {

  public static @NonNull ATRDto toATRDto(@NonNull ChartDto chartDto, @NonNull ATR atr) {
    return new ATRDto(atr.id(), chartDto, atr.timestamp(), atr.atr());
  }

  public static @NonNull ATR toATR(UUID id, @NonNull ChartDto chartDto, @NonNull ATRCreateDto atrCreateDto) {
    return new ATR(id, chartDto.id(), atrCreateDto.timestamp(), atrCreateDto.atr());
  }

}

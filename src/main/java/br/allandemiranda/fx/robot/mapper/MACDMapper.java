package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.MACDCreateDto;
import br.allandemiranda.fx.robot.dto.MACDDto;
import br.allandemiranda.fx.robot.model.MACD;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MACDMapper {

  public static @NonNull MACDDto toMACDDto(@NonNull ChartDto chartDto, @NonNull MACD macd) {
    return new MACDDto(macd.id(), chartDto, macd.timestamp(), macd.mainLine(), macd.signalLine());
  }

  public static @NonNull MACD toMACD(UUID id, @NonNull ChartDto chartDto, @NonNull MACDCreateDto macdCreateDto) {
    return new MACD(id, chartDto.id(), macdCreateDto.timestamp(), macdCreateDto.mainLine(), macdCreateDto.signalLine());
  }

}

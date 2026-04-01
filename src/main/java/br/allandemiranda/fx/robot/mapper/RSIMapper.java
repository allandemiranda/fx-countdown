package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.RSICreateDto;
import br.allandemiranda.fx.robot.dto.RSIDto;
import br.allandemiranda.fx.robot.model.RSI;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RSIMapper {

  public static @NonNull RSIDto toRSIDto(@NonNull ChartDto chartDto, @NonNull RSI rsi) {
    return new RSIDto(rsi.id(), chartDto, rsi.timestamp(), rsi.rsi());
  }

  public static @NonNull RSI toRSI(UUID id, @NonNull ChartDto chartDto, @NonNull RSICreateDto rsiCreateDto) {
    return new RSI(id, chartDto.id(), rsiCreateDto.timestamp(), rsiCreateDto.rsi());
  }

}

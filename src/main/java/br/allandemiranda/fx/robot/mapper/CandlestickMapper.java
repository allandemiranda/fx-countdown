package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.model.Candlestick;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CandlestickMapper {

  public static @NonNull CandlestickDto toCandlestickDto(@NonNull ChartDto chartDto, @NonNull Candlestick candlestick) {
    return new CandlestickDto(candlestick.id(), chartDto, candlestick.timestamp(), candlestick.open(), candlestick.high(), candlestick.low(), candlestick.close());
  }

  public static @NonNull Candlestick toCandlestick(UUID id, @NonNull ChartDto chartDto, @NonNull CandlestickCreateDto candlestickCreateDto) {
    return new Candlestick(id, chartDto.id(), candlestickCreateDto.timestamp(), candlestickCreateDto.open(), candlestickCreateDto.high(), candlestickCreateDto.low(), candlestickCreateDto.close());
  }

}

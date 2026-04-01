package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartCreateDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.model.Chart;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ChartMapper {

  public static @NonNull ChartDto toChartDto(@NonNull SymbolDto symbolDto, @NonNull Chart chart) {
    return new ChartDto(chart.id(), symbolDto, chart.period());
  }

  public static @NonNull Chart toChart(UUID id, @NonNull SymbolDto symbolDto, @NonNull ChartCreateDto chartCreateDto) {
    return new Chart(id, symbolDto.name(), chartCreateDto.period());
  }

}

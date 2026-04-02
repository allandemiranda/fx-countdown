package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.ChartCreateDto;
import br.allandemiranda.fx.robot.model.Chart;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ChartMapper {

  public ChartDto toDto(SymbolDto symbolDto, Chart chart) {
    return new ChartDto(chart.id(), symbolDto, chart.period());
  }

  public Chart toModel(UUID id, SymbolDto symbolDto, ChartCreateDto chartCreateDto) {
    return new Chart(id, symbolDto.name(), chartCreateDto.period());
  }

}

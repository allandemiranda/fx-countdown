package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.ChartCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.Chart;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChartMapperTest {

  @InjectMocks
  private ChartMapper chartMapper;

  @Test
  void toDto_test() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    Chart chart = Mockito.mock(Chart.class);
    UUID id = Mockito.mock(UUID.class);
    Timeframe timeframe = Mockito.mock(Timeframe.class);

    //when
    Mockito.when(chart.id()).thenReturn(id);
    Mockito.when(chart.period()).thenReturn(timeframe);
    ChartDto chartDto = this.chartMapper.toDto(symbolDto, chart);

    //then
    Assertions.assertNotNull(chartDto);
    Assertions.assertEquals(symbolDto, chartDto.symbol());
    Assertions.assertNotNull(chartDto.id());
    Assertions.assertNotNull(chartDto.period());
  }

  @Test
  void toModel_test() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    ChartCreateDto chartCreateDto = Mockito.mock(ChartCreateDto.class);
    Timeframe timeframe = Mockito.mock(Timeframe.class);
    UUID id = Mockito.mock(UUID.class);

    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(chartCreateDto.period()).thenReturn(timeframe);
    Chart chart = this.chartMapper.toModel(id, symbolDto, chartCreateDto);

    //then
    Assertions.assertNotNull(chart);
    Assertions.assertEquals(id, chart.id());
    Assertions.assertEquals(symbolName, chart.symbolName());
    Assertions.assertEquals(timeframe, chart.period());
  }
}
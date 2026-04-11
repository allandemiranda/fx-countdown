package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.ChartCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.ChartMapper;
import br.allandemiranda.fx.robot.model.Chart;
import br.allandemiranda.fx.robot.repository.ChartRepository;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ChartServiceTest {

  @InjectMocks
  private ChartService service;

  @Mock
  private ChartRepository repository;

  @Spy
  private ChartMapper mapper;

  @Test
  void getBySymbolAndTimeframe_ShouldReturnDto_WhenModelExists() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    Timeframe period = Mockito.mock(Timeframe.class);
    Chart chart = Mockito.mock(Chart.class);
    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.repository.findBySymbolNameAndPeriod(symbolDto.name(), period)).thenReturn(Mono.just(chart));
    Mockito.when(chart.period()).thenReturn(period);
    //then
    StepVerifier.create(this.service.get(symbolDto, period)).assertNext(dto -> {
      Assertions.assertNotNull(dto);
      Assertions.assertEquals(symbolDto, dto.symbol());
      Assertions.assertEquals(symbolName, dto.symbol().name());
      Assertions.assertEquals(period, dto.period());
    }).verifyComplete();
  }

  @Test
  void getBySymbolAndTimeframe_ShouldReturnVoid_WhenModelNotExists() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    Timeframe period = Mockito.mock(Timeframe.class);
    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.repository.findBySymbolNameAndPeriod(symbolDto.name(), period)).thenReturn(Mono.empty());
    //then
    StepVerifier.create(this.service.get(symbolDto, period)).expectSubscription().expectNextCount(0).expectComplete().verify();
  }

  @Test
  void getAll_ShouldReturnFluxOfDtos() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    Chart chart = Mockito.mock(Chart.class);
    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.repository.findBySymbolName(symbolDto.name())).thenReturn(Flux.just(chart));
    //then
    StepVerifier.create(this.service.get(symbolDto)).assertNext(dto -> {
      Assertions.assertNotNull(dto);
      Assertions.assertEquals(symbolDto, dto.symbol());
      Assertions.assertEquals(symbolName, dto.symbol().name());
    }).verifyComplete();
  }

  @Test
  void getAll_ShouldReturnEmptyFlux_IfNotExistsChartDto() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.repository.findBySymbolName(symbolName)).thenReturn(Flux.just());
    //then
    StepVerifier.create(this.service.get(symbolDto).collectList()).assertNext(dtos -> {
      Assertions.assertEquals(0, dtos.size());
    }).verifyComplete();
  }

  @Test
  void create_ShouldReturnModel() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    ChartCreateDto chartCreateDto = Mockito.mock(ChartCreateDto.class);
    Timeframe period = Mockito.mock(Timeframe.class);
    Chart chart = Mockito.mock(Chart.class);
    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(chartCreateDto.period()).thenReturn(period);
    Mockito.when(chartCreateDto.period()).thenReturn(period);
    Mockito.doReturn(chartCreateDto.period()).when(chart).period();
    Mockito.doReturn(chart).when(mapper).toModel(Mockito.any(UUID.class), Mockito.eq(symbolDto), Mockito.eq(chartCreateDto));
    Mockito.when(repository.save(chart)).thenReturn(Mono.just(chart));
    //then
    StepVerifier.create(this.service.create(symbolDto, chartCreateDto)).assertNext(dto -> {
      Assertions.assertNotNull(dto);
      Assertions.assertNotNull(dto.symbol());
      Assertions.assertEquals(symbolName, dto.symbol().name());
      Assertions.assertNotNull(dto.period());
      Assertions.assertEquals(period, dto.period());
    }).verifyComplete();
  }

  @Test
  void delete_ShouldReturnEmptyMono() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    Timeframe period = Mockito.mock(Timeframe.class);
    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(repository.deleteAllBySymbolNameAndPeriod(symbolDto.name(), period)).thenReturn(Mono.empty());
    //then
    StepVerifier.create(service.delete(symbolDto, period)).expectNextCount(0).verifyComplete();
  }
}
package br.allandemiranda.fx.robot.controller.contract;


import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractChartChartObjectControllerTest<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> {

  @MockitoBean
  private SymbolService symbolService;

  @MockitoBean
  private ChartService chartService;

  protected abstract WebTestClient getWebTestClient();

  protected abstract ChartObjectService<M, D, C> getService();

  protected abstract String getChartObjectName();

  protected abstract String getBaseUrl();

  @Test
  void check_test() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    Timeframe period = Mockito.mock(Timeframe.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    D chartObjectDto = (D) new ChartObjectDto(){

      @Override
      public UUID id() {
        return null;
      }

      @Override
      public ChartDto chartDto() {
        return null;
      }

      @Override
      public OffsetDateTime timestamp() {
        return null;
      }
    };

    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));
    Mockito.when(chartDto.symbol()).thenReturn(symbolDto);
    Mockito.when(chartDto.period()).thenReturn(period);
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.just(chartDto));
    Mockito.when(this.getService().get(chartDto)).thenReturn(Flux.just(chartObjectDto));

    //then
    this.getWebTestClient().get()
        .uri(this.getBaseUrl().concat(""), symbolName, period)
        .exchange()
        .expectStatus().isOk();
  }

}
package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.base.ADXDto;
import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.service.ADXService;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = ADXController.class)
class ADXControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockitoBean
  private SymbolService symbolService;

  @MockitoBean
  private ChartService chartService;

  @MockitoBean
  private ADXService service;

  @Test
  void find_byNameAndPeriodAndTimestamp_valid_returnDto_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    OffsetDateTime timestamp = OffsetDateTime.now();

    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = Mockito.mock(UUID.class);

    ADXDto dto = Mockito.mock(ADXDto.class);
    UUID dtoId = Mockito.mock(UUID.class);

    //when
    Mockito.when(symbolDto.name()).thenReturn(name);
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));

    Mockito.when(chartDto.id()).thenReturn(chartId);
    Mockito.when(chartDto.symbol()).thenReturn(symbolDto);
    Mockito.when(chartDto.period()).thenReturn(period);
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.just(chartDto));

    Mockito.when(dto.id()).thenReturn(dtoId);
    Mockito.when(dto.chartDto()).thenReturn(chartDto);
    Mockito.when(dto.timestamp()).thenReturn(timestamp);
    Mockito.when(this.service.get(chartDto, timestamp)).thenReturn(Mono.just(dto));

    //then
    this.webTestClient
        .get()
        .uri("/symbols/{name}/timeframes/{period}/adxs/{timestamp}", name, period, timestamp)
        .exchange()
        .expectStatus().isOk()
        .expectBody(ADXDto.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals(dto, response);
        });
  }

  @Test
  void find_byNameAndPeriodAndTimestamp_notValidName_returnEmpty_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    OffsetDateTime timestamp = OffsetDateTime.now();

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.empty());

    //then
    System.out.println(this.webTestClient
        .get()
        .uri("/symbols/{name}/timeframes/{period}/adxs/{timestamp}", name, period, timestamp)
        .exchange()
        .expectStatus().isNotFound()
        .expectBody()
        .returnResult().toString());

  }
}
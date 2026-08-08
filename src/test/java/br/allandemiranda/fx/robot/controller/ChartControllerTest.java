package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.advice.CodeResponseHandler;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.create.ChartCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(controllers = ChartController.class)
class ChartControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockitoBean
  private SymbolService symbolService;

  @MockitoBean
  private ChartService chartService;

  @Nested
  class FindAll {

    @Test
    void validSymbolName_returnFluxDto_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";

      ChartDto chartDto = Mockito.mock(ChartDto.class);
      UUID chartId = UUID.randomUUID();

      //when
      Mockito.when(symbolDto.name()).thenReturn(symbolName);
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));

      Mockito.when(chartDto.id()).thenReturn(chartId);
      Mockito.when(chartDto.symbol()).thenReturn(symbolDto);
      Mockito.when(chartService.get(symbolDto)).thenReturn(Flux.just(chartDto));

      webTestClient
          .get()
          .uri("/symbols/{symbolName}/charts", symbolName)
          .exchange()

          //then
          .expectStatus().isOk()
          .expectBodyList(ChartDto.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals(1, response.size());

            ChartDto responseDto = response.getFirst();
            Assertions.assertNotNull(responseDto);
            Assertions.assertEquals(chartId, responseDto.id());
            Assertions.assertEquals(symbolDto.name(), responseDto.symbol().name());
          });
    }

    @Test
    void validSymbolName_empty_returnEmptyFluxDto_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));
      Mockito.when(chartService.get(symbolDto)).thenReturn(Flux.empty());

      webTestClient
          .get()
          .uri("/symbols/{symbolName}/charts", symbolName)
          .exchange()

          //then
          .expectStatus().isOk()
          .expectBodyList(ChartDto.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals(0, response.size());
          });
    }

    @Test
    void notValidSymbolName_returnFluxDto_test() {
      //given
      String symbolName = "EURUSD";

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.empty());

      webTestClient
          .get()
          .uri("/symbols/{symbolName}/charts", symbolName)
          .exchange()

          //then
          .expectStatus().isNotFound()
          .expectBody(CodeResponseHandler.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals("SymbolNotFoundException", response.type());
            Assertions.assertEquals("Symbol not found: [" + symbolName + "]", response.message());
          });
    }

  }

  @Nested
  class Find {

    @Test
    void validSymbolName_validTimeframes_returnDto_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";

      ChartDto chartDto = Mockito.mock(ChartDto.class);
      UUID chartId = UUID.randomUUID();
      Timeframe timeframe = Timeframe.values()[0];

      //when
      Mockito.when(symbolDto.name()).thenReturn(symbolName);
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));

      Mockito.when(chartDto.id()).thenReturn(chartId);
      Mockito.when(chartDto.symbol()).thenReturn(symbolDto);
      Mockito.when(chartDto.period()).thenReturn(timeframe);
      Mockito.when(chartService.get(symbolDto, timeframe)).thenReturn(Mono.just(chartDto));

      webTestClient
          .get()
          .uri("/symbols/{symbolName}/timeframes/{timeframe}", symbolName, timeframe)
          .exchange()

          //then
          .expectStatus().isOk()
          .expectBody(ChartDto.class)
          .value(response -> {
            Assertions.assertNotNull(response);

            Assertions.assertEquals(chartId, response.id());
            Assertions.assertEquals(timeframe, response.period());
            Assertions.assertEquals(symbolDto.name(), response.symbol().name());
          });
    }

    @Test
    void notValidSymbolName_returnNotFound_test() {
      //given
      String symbolName = "EURUSD";
      Timeframe timeframe = Timeframe.values()[0];

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.empty());

      webTestClient
          .get()
          .uri("/symbols/{symbolName}/timeframes/{timeframe}", symbolName, timeframe)
          .exchange()

          //then
          .expectStatus().isNotFound()
          .expectBody(CodeResponseHandler.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals("SymbolNotFoundException", response.type());
            Assertions.assertEquals("Symbol not found: [" + symbolName + "]", response.message());
          });
    }

    @Test
    void validSymbolName_notValidTimeframes_returnNotFound_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";
      Timeframe timeframe = Timeframe.values()[0];

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));
      Mockito.when(chartService.get(symbolDto, timeframe)).thenReturn(Mono.empty());

      webTestClient
          .get()
          .uri("/symbols/{symbolName}/timeframes/{timeframe}", symbolName, timeframe)
          .exchange()

          //then
          .expectStatus().isNotFound()
          .expectBody(CodeResponseHandler.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals("ChartNotFoundException", response.type());
            Assertions.assertEquals("Chart not found: [" + symbolName + ", " + timeframe.getCode() + "]", response.message());
          });
    }
  }

  @Nested
  class Create {

    @Test
    void validSymbolName_validChartCreateDto_returnDto_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";
      Timeframe timeframe = Timeframe.values()[0];
      ChartCreateDto chartCreateDto = new ChartCreateDto(timeframe);
      ChartDto chartDto = Mockito.mock(ChartDto.class);

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));
      Mockito.when(chartDto.period()).thenReturn(timeframe);
      Mockito.when(chartService.create(symbolDto, chartCreateDto)).thenReturn(Mono.just(chartDto));

      webTestClient
          .post()
          .uri("/symbols/{symbolName}/charts", symbolName)
          .bodyValue(chartCreateDto)
          .exchange()

          //then
          .expectStatus().isCreated()
          .expectBody(ChartCreateDto.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals(timeframe, response.period());
          });
    }

    @Test
    void notValidSymbolName_validChartCreateDto_returnDto_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";
      Timeframe timeframe = Timeframe.values()[0];
      ChartCreateDto chartCreateDto = new ChartCreateDto(timeframe);
      ChartDto chartDto = Mockito.mock(ChartDto.class);

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.empty());
      Mockito.when(chartService.create(symbolDto, chartCreateDto)).thenReturn(Mono.just(chartDto));

      webTestClient
          .post()
          .uri("/symbols/{symbolName}/charts", symbolName)
          .bodyValue(chartCreateDto)
          .exchange()

          //then
          .expectStatus().isNotFound()
          .expectBody(CodeResponseHandler.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals("SymbolNotFoundException", response.type());
            Assertions.assertEquals("Symbol not found: [" + symbolName + "]", response.message());
          });
    }

    @Test
    void validSymbolName_missingChartCreateDto_returnDto_test() {
      //given
      String symbolName = "EURUSD";

      //when
      webTestClient
          .post()
          .uri("/symbols/{symbolName}/charts", symbolName)
          .exchange()

          //then
          .expectStatus().isBadRequest()
          .expectBody(ProblemDetail.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals("Invalid request content", response.getDetail());
            Assertions.assertEquals(400, response.getStatus());
            Assertions.assertEquals("Bad Request", response.getTitle());
          });
    }

    @Test
    void validSymbolName_validChartCreateDto_returnEmptyDto_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";
      Timeframe timeframe = Timeframe.values()[0];
      ChartCreateDto chartCreateDto = new ChartCreateDto(timeframe);

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));
      Mockito.when(chartService.create(symbolDto, chartCreateDto)).thenReturn(Mono.empty());

      webTestClient
          .post()
          .uri("/symbols/{symbolName}/charts", symbolName)
          .bodyValue(chartCreateDto)
          .exchange()

          //then
          .expectStatus().is5xxServerError();
    }
  }

  @Nested
  class Delete {

    @Test
    void byNameAndPeriod_exist_returnVoid_test() {
      //given
      String symbolName = "EURUSD";
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      Timeframe timeframe = Timeframe.values()[0];
      ChartDto chartDto = Mockito.mock(ChartDto.class);

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));
      Mockito.when(chartService.get(symbolDto, timeframe)).thenReturn(Mono.just(chartDto));
      Mockito.when(chartService.delete(chartDto)).thenReturn(Mono.empty());

      webTestClient
          .delete()
          .uri("/symbols/{symbolName}/timeframes/{timeframe}", symbolName, timeframe)
          .exchange()

          //then
          .expectStatus().isNoContent();
    }

    @Test
    void byNameAndPeriod_nameNotExist_returnError_test() {
      //given
      String symbolName = "EURUSD";
      Timeframe timeframe = Timeframe.values()[0];

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.empty());

      webTestClient
          .delete()
          .uri("/symbols/{symbolName}/timeframes/{timeframe}", symbolName, timeframe)
          .exchange()

          //then
          .expectStatus().isNotFound()
          .expectBody(CodeResponseHandler.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals("SymbolNotFoundException", response.type());
            Assertions.assertEquals("Symbol not found: [" + symbolName + "]", response.message());
          });
    }

    @Test
    void byNameAndPeriod_periodNotExist_returnError_test() {
      //given
      String symbolName = "EURUSD";
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      Timeframe timeframe = Timeframe.values()[0];
      ChartDto chartDto = Mockito.mock(ChartDto.class);

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));
      Mockito.when(chartService.get(symbolDto, timeframe)).thenReturn(Mono.empty());
      Mockito.when(chartService.delete(chartDto)).thenReturn(Mono.empty());

      webTestClient
          .delete()
          .uri("/symbols/{symbolName}/timeframes/{timeframe}", symbolName, timeframe)
          .exchange()

          //then
          .expectStatus().isNotFound()
          .expectBody(CodeResponseHandler.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals("ChartNotFoundException", response.type());
            Assertions.assertEquals("Chart not found: [" + symbolName + ", " + timeframe.getCode() + "]", response.message());
          });
    }
  }

}
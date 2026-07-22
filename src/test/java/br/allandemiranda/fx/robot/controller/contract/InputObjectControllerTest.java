package br.allandemiranda.fx.robot.controller.contract;

import br.allandemiranda.fx.robot.controller.advice.CodeResponseHandler;
import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import java.net.URI;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public abstract class InputObjectControllerTest<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto> extends EssentialServicesTest {

  protected abstract WebTestClient getWebTestClient();

  protected abstract InputObjectController<M, D, C> getController();

  protected abstract D getDto();

  protected abstract C getCreateDto();

  protected abstract void setupCreateDto();

  protected abstract InputObjectService<M, D, C> getService();

  private @NonNull String getObjectUri() {
    return this.getController().getInputObjectName().toLowerCase().replace(' ', '_').concat("s");
  }

  @Test
  void find_byNameAndPeriod_timestampOnPresent_exist_returnDto_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = UUID.randomUUID();

    D dto = this.getDto();

    //when
    Mockito.when(symbolDto.name()).thenReturn(name);
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.just(symbolDto));

    Mockito.when(chartDto.id()).thenReturn(chartId);
    Mockito.when(chartDto.symbol()).thenReturn(symbolDto);
    Mockito.when(chartDto.period()).thenReturn(period);
    Mockito.when(this.getChartService().get(symbolDto, period)).thenReturn(Mono.just(chartDto));

    Mockito.when(dto.chartDto()).thenReturn(chartDto);
    Mockito.when(this.getService().get(chartDto)).thenReturn(Mono.just(dto));

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isOk()
        .expectBody(this.getDto().getClass())
        .value(response -> {
          Assertions.assertNotNull(response);

          Assertions.assertNotNull(response.chartDto());
          Assertions.assertEquals(dto.chartDto().id(), response.chartDto().id());
          Assertions.assertEquals(dto.chartDto().period(), response.chartDto().period());

          Assertions.assertNotNull(response.chartDto().symbol());
          Assertions.assertEquals(dto.chartDto().symbol().name(), response.chartDto().symbol().name());
        });
  }

  @Test
  void find_byNameAndPeriod_timestampOnPast_exist_returnDto_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = UUID.randomUUID();

    D dto = this.getDto();

    //when
    Mockito.when(symbolDto.name()).thenReturn(name);
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.just(symbolDto));

    Mockito.when(chartDto.id()).thenReturn(chartId);
    Mockito.when(chartDto.symbol()).thenReturn(symbolDto);
    Mockito.when(chartDto.period()).thenReturn(period);
    Mockito.when(this.getChartService().get(symbolDto, period)).thenReturn(Mono.just(chartDto));

    Mockito.when(dto.chartDto()).thenReturn(chartDto);
    Mockito.when(this.getService().get(chartDto)).thenReturn(Mono.just(dto));

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isOk()
        .expectBody(this.getDto().getClass())
        .value(response -> {
          Assertions.assertNotNull(response);

          Assertions.assertNotNull(response.chartDto());
          Assertions.assertEquals(dto.chartDto().id(), response.chartDto().id());
          Assertions.assertEquals(dto.chartDto().period(), response.chartDto().period());

          Assertions.assertNotNull(response.chartDto().symbol());
          Assertions.assertEquals(dto.chartDto().symbol().name(), response.chartDto().symbol().name());
        });
  }

  @Test
  void find_byNameAndPeriod_notExistTimestamp_returnNotFoundChartObject_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.getChartService().get(symbolDto, period)).thenReturn(Mono.just(chartDto));
    Mockito.when(this.getService().get(chartDto)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("InputObjectNotFoundException", response.type());
          Assertions.assertEquals("Input object not found: [" + name + ", " + period + ", " + this.getController().getInputObjectName() + "]", response.message());
        });
  }

  @Test
  void find_byNameAndPeriod_notExistName_returnNotFoundSymbol_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("SymbolNotFoundException", response.type());
          Assertions.assertEquals("Symbol not found: [" + name + "]", response.message());
        });
  }

  @Test
  void find_byNameAndPeriod_notExistPeriod_returnNotFoundChart_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.getChartService().get(symbolDto, period)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("ChartNotFoundException", response.type());
          Assertions.assertEquals("Chart not found: [" + name + ", " + period.getCode() + "]", response.message());
        });
  }

  @Test
  void find_byNameAndPeriod_notExistNameAndPeriod_returnNotFoundSymbol_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("SymbolNotFoundException", response.type());
          Assertions.assertEquals("Symbol not found: [" + name + "]", response.message());
        });
  }

  @Test
  void find_byNameAndPeriod_notValidPeriod_returnBadRequest_test() {
    //given
    String name = "EURUSD";
    String period = RandomStringUtils.insecure().nextAlphanumeric(1);

    //when
    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isBadRequest()
        .expectBody(ProblemDetail.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("Type mismatch.", response.getDetail());
          Assertions.assertNotNull(response.getInstance());
          Assertions.assertEquals(URI.create(String.format("/symbols/%s/timeframes/%s/" + this.getObjectUri(), name, period)).toASCIIString(), response.getInstance().toASCIIString().replace("%3A", ":"));
          Assertions.assertEquals(400, response.getStatus());
          Assertions.assertEquals("Bad Request", response.getTitle());
        });
  }

  @Test
  void create_withValidNameAndPeriod_returnCreated_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = UUID.randomUUID();

    this.setupCreateDto();

    D dto = this.getDto();

    //when
    Mockito.when(symbolDto.name()).thenReturn(name);
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.just(symbolDto));

    Mockito.when(chartDto.id()).thenReturn(chartId);
    Mockito.when(chartDto.symbol()).thenReturn(symbolDto);
    Mockito.when(chartDto.period()).thenReturn(period);
    Mockito.when(this.getChartService().get(symbolDto, period)).thenReturn(Mono.just(chartDto));

    Mockito.when(dto.chartDto()).thenReturn(chartDto);
    Mockito.when(this.getService().create(Mockito.eq(chartDto), Mockito.any())).thenReturn(Mono.just(dto));

    //then
    this.getWebTestClient()
        .post()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .bodyValue(this.getCreateDto())
        .exchange()
        .expectStatus().isCreated()
        .expectBody(this.getDto().getClass())
        .value(response -> {
          Assertions.assertNotNull(response);

          Assertions.assertNotNull(response.chartDto());
          Assertions.assertEquals(chartDto.id(), response.chartDto().id());
          Assertions.assertEquals(chartDto.period(), response.chartDto().period());

          Assertions.assertNotNull(response.chartDto().symbol());
          Assertions.assertEquals(symbolDto.name(), response.chartDto().symbol().name());
        });
  }

  @Test
  void create_withNameAndPeriod_notExistName_returnNotFoundSymbol_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    this.setupCreateDto();

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .post()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .bodyValue(this.getCreateDto())
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("SymbolNotFoundException", response.type());
          Assertions.assertEquals("Symbol not found: [" + name + "]", response.message());
        });
  }

  @Test
  void create_withNameAndPeriod_notExistPeriod_returnNotFoundChart_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    this.setupCreateDto();

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.getChartService().get(symbolDto, period)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .post()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .bodyValue(this.getCreateDto())
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("ChartNotFoundException", response.type());
          Assertions.assertEquals("Chart not found: [" + name + ", " + period.getCode() + "]", response.message());
        });
  }

  @Test
  void deleteAll_byNameAndPeriod_valid_existValues_returnMonoVoid_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.getChartService().get(symbolDto, period)).thenReturn(Mono.just(chartDto));
    Mockito.when(this.getService().delete(chartDto)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .delete()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isNoContent()
        .expectBodyList(Void.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals(0, response.size());

          Mockito.verify(this.getService(), Mockito.times(1)).delete(chartDto);
        });
  }

  @Test
  void deleteAll_byNameAndPeriod_valid_notExistValues_returnMonoVoid_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.getChartService().get(symbolDto, period)).thenReturn(Mono.just(chartDto));
    Mockito.when(this.getService().delete(chartDto)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .delete()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isNoContent()
        .expectBodyList(this.getDto().getClass())
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals(0, response.size());

          Mockito.verify(this.getService(), Mockito.times(1)).delete(chartDto);
        });
  }

  @Test
  void deleteAll_byNameAndPeriod_nameAndPeriodNotExist_returnNotFoundSymbol_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .delete()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("SymbolNotFoundException", response.type());
          Assertions.assertEquals("Symbol not found: [" + name + "]", response.message());
        });
  }

  @Test
  void deleteAll_byNameAndPeriod_nameNotExist_returnNotFoundSymbol_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .delete()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("SymbolNotFoundException", response.type());
          Assertions.assertEquals("Symbol not found: [" + name + "]", response.message());
        });
  }

  @Test
  void deleteAll_byNameAndPeriod_periodNotExist_returnNotFoundChart_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    //when
    Mockito.when(this.getSymbolService().get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.getChartService().get(symbolDto, period)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getObjectUri(), name, period)
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("ChartNotFoundException", response.type());
          Assertions.assertEquals("Chart not found: [" + name + ", " + period.getCode() + "]", response.message());
        });
  }
}
package br.allandemiranda.fx.robot.controller.contract;

import br.allandemiranda.fx.robot.controller.advice.CodeResponseHandler;
import br.allandemiranda.fx.robot.controller.advice.ViolationResponseHandler;
import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ProblemDetail;
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

  protected abstract ChartObjectController<M, D, C> getController();

  protected abstract D getDto();

  protected abstract C getCreateDto();

  protected abstract void setupCreateDto();

  protected abstract ChartObjectService<M, D, C> getService();

  @Test
  void findAll_byNameAndPeriod_valid_existValues_returnFluxDto_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = UUID.randomUUID();

    D dto = this.getDto();
    UUID dtoId = UUID.randomUUID();

    //when
    Mockito.when(symbolDto.name()).thenReturn(name);
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));

    Mockito.when(chartDto.id()).thenReturn(chartId);
    Mockito.when(chartDto.symbol()).thenReturn(symbolDto);
    Mockito.when(chartDto.period()).thenReturn(period);
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.just(chartDto));

    Mockito.when(dto.id()).thenReturn(dtoId);
    Mockito.when(dto.chartDto()).thenReturn(chartDto);
    Mockito.when(this.getService().get(chartDto)).thenReturn(Flux.just(dto));

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
        .exchange()

        //then
        .expectStatus().isOk()
        .expectBodyList(this.getDto().getClass())
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals(1, response.size());

          D reportDto = (D) response.getFirst();
          Assertions.assertEquals(dto.id(), reportDto.id());
          Assertions.assertEquals(dto.timestamp(), reportDto.timestamp());

          Assertions.assertNotNull(reportDto.chartDto());
          Assertions.assertEquals(dto.chartDto().id(), reportDto.chartDto().id());
          Assertions.assertEquals(dto.chartDto().period(), reportDto.chartDto().period());

          Assertions.assertNotNull(reportDto.chartDto().symbol());
          Assertions.assertEquals(dto.chartDto().symbol().name(), reportDto.chartDto().symbol().name());
        });
  }

  @Test
  void findAll_byNameAndPeriod_valid_notExistValues_returnFluxEmpty_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.just(chartDto));
    Mockito.when(this.getService().get(chartDto)).thenReturn(Flux.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
        .exchange()

        //then
        .expectStatus().isOk()
        .expectBodyList(this.getDto().getClass())
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals(0, response.size());
        });
  }


  @Test
  void findAll_byNameAndPeriod_nameAndPeriodNotExist_returnNotFoundSymbol_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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
  void findAll_byNameAndPeriod_nameNotExist_returnNotFoundSymbol_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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
  void findAll_byNameAndPeriod_periodNotExist_returnNotFoundChart_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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
  void find_byNameAndPeriodAndTimestamp_timestampOnPresent_exist_returnDto_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = UUID.randomUUID();

    D dto = this.getDto();
    UUID dtoId = UUID.randomUUID();

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
    Mockito.when(this.getService().get(chartDto, timestamp)).thenReturn(Mono.just(dto));

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s") + "/{timestamp}", name, period, timestamp)
        .exchange()

        //then
        .expectStatus().isOk()
        .expectBody(this.getDto().getClass())
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals(dto.id(), response.id());
          Assertions.assertEquals(dto.timestamp(), response.timestamp());

          Assertions.assertNotNull(response.chartDto());
          Assertions.assertEquals(dto.chartDto().id(), response.chartDto().id());
          Assertions.assertEquals(dto.chartDto().period(), response.chartDto().period());

          Assertions.assertNotNull(response.chartDto().symbol());
          Assertions.assertEquals(dto.chartDto().symbol().name(), response.chartDto().symbol().name());
        });
  }

  @Test
  void find_byNameAndPeriodAndTimestamp_timestampOnPast_exist_returnDto_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC).minusDays(1);

    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = UUID.randomUUID();

    D dto = this.getDto();
    UUID dtoId = UUID.randomUUID();

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
    Mockito.when(this.getService().get(chartDto, timestamp)).thenReturn(Mono.just(dto));

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s") + "/{timestamp}", name, period, timestamp)
        .exchange()

        //then
        .expectStatus().isOk()
        .expectBody(this.getDto().getClass())
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals(dto.id(), response.id());
          Assertions.assertEquals(dto.timestamp(), response.timestamp());

          Assertions.assertNotNull(response.chartDto());
          Assertions.assertEquals(dto.chartDto().id(), response.chartDto().id());
          Assertions.assertEquals(dto.chartDto().period(), response.chartDto().period());

          Assertions.assertNotNull(response.chartDto().symbol());
          Assertions.assertEquals(dto.chartDto().symbol().name(), response.chartDto().symbol().name());
        });
  }

  @Test
  void find_byNameAndPeriodAndTimestamp_notExistName_returnNotFoundSymbol_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s") + "/{timestamp}", name, period, timestamp)
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
  void find_byNameAndPeriodAndTimestamp_notExistPeriod_returnNotFoundChart_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s") + "/{timestamp}", name, period, timestamp)
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
  void find_byNameAndPeriodAndTimestamp_notExistTimestamp_returnNotFoundChartObject_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.just(chartDto));
    Mockito.when(this.getService().get(chartDto, timestamp)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s") + "/{timestamp}", name, period, timestamp)
        .exchange()

        //then
        .expectStatus().isNotFound()
        .expectBody(CodeResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("ChartObjectNotFoundException", response.type());
          Assertions.assertEquals("Chart object not found: [" + name + ", " + period + ", " + this.getController().getChartObjectName() + ", " + timestamp + "]", response.message());
        });
  }

  @Test
  void find_byNameAndPeriodAndTimestamp_notExistNameAndPeriodAndTimestamp_returnNotFoundSymbol_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s") + "/{timestamp}", name, period, timestamp)
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
  void find_byNameAndPeriodAndTimestamp_notValidPeriod_returnBadRequest_test() {
    //given
    String name = "EURUSD";
    String period = RandomStringUtils.insecure().nextAlphanumeric(1);
    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

    //when
    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s") + "/{timestamp}", name, period, timestamp)
        .exchange()

        //then
        .expectStatus().isBadRequest()
        .expectBody(ProblemDetail.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("Type mismatch.", response.getDetail());
          Assertions.assertNotNull(response.getInstance());
          Assertions.assertEquals(URI.create(String.format("/symbols/%s/timeframes/%s/" + this.getController().getChartObjectName().toLowerCase().concat("s") + "/%s", name, period, timestamp)).toASCIIString(), response.getInstance().toASCIIString().replace("%3A", ":"));
          Assertions.assertEquals(400, response.getStatus());
          Assertions.assertEquals("Bad Request", response.getTitle());
        });
  }

  @Test
  void find_byNameAndPeriodAndTimestamp_notValidTimestamp_timestampOnFuture_returnBadRequest_test() {
    //given
    String name = "EURUSD";
    Timeframe period = Timeframe.values()[0];
    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC).plusSeconds(1);

    //when
    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s") + "/{timestamp}", name, period, timestamp)
        .exchange()

        //then
        .expectStatus().isBadRequest()
        .expectBody(ViolationResponseHandler.class)
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertEquals("Constraint violation", response.error());
          Assertions.assertNotNull(response.message());
          Assertions.assertFalse(response.message().isEmpty());
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

    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    Mockito.when(this.getCreateDto().timestamp()).thenReturn(timestamp);
    this.setupCreateDto();

    D dto = this.getDto();
    UUID dtoId = UUID.randomUUID();

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
    Mockito.when(this.getService().create(Mockito.eq(chartDto), Mockito.any())).thenReturn(Mono.just(dto));

    //then
    this.getWebTestClient()
        .post()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
        .bodyValue(this.getCreateDto())
        .exchange()
        .expectStatus().isCreated()
        .expectBody(this.getDto().getClass())
        .value(response -> {
          Assertions.assertNotNull(response);
          Assertions.assertNotNull(response.id());
          Assertions.assertEquals(this.getCreateDto().timestamp(), response.timestamp());

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

    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    Mockito.when(this.getCreateDto().timestamp()).thenReturn(timestamp);
    this.setupCreateDto();

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .post()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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

    OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    Mockito.when(this.getCreateDto().timestamp()).thenReturn(timestamp);
    this.setupCreateDto();

    //when
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .post()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.just(chartDto));
    Mockito.when(this.getService().delete(chartDto)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .delete()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.just(chartDto));
    Mockito.when(this.getService().delete(chartDto)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .delete()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .delete()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .delete()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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
    Mockito.when(this.symbolService.get(name)).thenReturn(Mono.just(symbolDto));
    Mockito.when(this.chartService.get(symbolDto, period)).thenReturn(Mono.empty());

    this.getWebTestClient()
        .get()
        .uri("/symbols/{name}/timeframes/{period}/" + this.getController().getChartObjectName().toLowerCase().concat("s"), name, period)
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
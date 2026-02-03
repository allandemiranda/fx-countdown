package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.model.impl.Dashboard;
import br.allandemiranda.fx.robot.service.TickService;
import br.allandemiranda.fx.robot.service.impl.ADXService;
import br.allandemiranda.fx.robot.service.impl.ATRService;
import br.allandemiranda.fx.robot.service.impl.BandsService;
import br.allandemiranda.fx.robot.service.impl.CandlestickService;
import br.allandemiranda.fx.robot.service.impl.DashboardService;
import br.allandemiranda.fx.robot.service.impl.GarchForecastService;
import br.allandemiranda.fx.robot.service.impl.GarchInputService;
import br.allandemiranda.fx.robot.service.impl.GarchTradingService;
import br.allandemiranda.fx.robot.service.impl.MACDService;
import br.allandemiranda.fx.robot.service.impl.MaFastService;
import br.allandemiranda.fx.robot.service.impl.MaSlowService;
import br.allandemiranda.fx.robot.service.impl.RSIService;
import br.allandemiranda.fx.robot.service.impl.StochasticService;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.Executor;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = DashboardController.class)
class DashboardControllerTest extends InputObjectControllerTest<Dashboard, DashboardDto, DashboardCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private DashboardController controller;

  @Mock
  @Getter
  private DashboardDto dto;

  @Mock
  @Getter
  private DashboardCreateDto createDto;

  @Getter
  @MockitoBean
  private DashboardService service;

  @MockitoBean
  private ADXService adxService;

  @MockitoBean
  private ATRService atrService;

  @MockitoBean
  private BandsService bandsService;

  @MockitoBean
  private CandlestickService candlestickService;

  @MockitoBean
  private MACDService macdService;

  @MockitoBean
  private MaFastService maFastService;

  @MockitoBean
  private MaSlowService maSlowService;

  @MockitoBean
  private RSIService rsiService;

  @MockitoBean
  private StochasticService stochasticService;

  @MockitoBean
  private TickService tickService;

  @MockitoBean
  private Executor executor;

  @MockitoBean
  private GarchInputService garchInputService;

  @MockitoBean
  private GarchForecastService garchForecastService;

  @MockitoBean
  private GarchTradingService garchTradingService;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.status()).thenReturn(DashboardStatus.values()[0]);
    Mockito.when(this.createDto.startScope()).thenReturn(OffsetDateTime.now(ZoneOffset.UTC));
    Mockito.when(this.createDto.endScope()).thenReturn(OffsetDateTime.now(ZoneOffset.UTC));
  }
}
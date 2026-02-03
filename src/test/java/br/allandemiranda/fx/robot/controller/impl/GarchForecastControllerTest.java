package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.model.impl.GarchForecast;
import br.allandemiranda.fx.robot.service.impl.GarchForecastService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = GarchForecastController.class)
class GarchForecastControllerTest extends ChartObjectControllerTest<GarchForecast, GarchForecastDto, GarchForecastCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private GarchForecastController controller;

  @Mock
  @Getter
  private GarchForecastDto dto;

  @Mock
  @Getter
  private GarchForecastCreateDto createDto;

  @Getter
  @MockitoBean
  private GarchForecastService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.omega()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.alpha()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.beta()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.sigmaAgg()).thenReturn(BigDecimal.ONE);
  }
}
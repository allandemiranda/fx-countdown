package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.model.impl.Stochastic;
import br.allandemiranda.fx.robot.service.impl.StochasticService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = StochasticController.class)
class StochasticControllerTest extends ChartObjectControllerTest<Stochastic, StochasticDto, StochasticCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private StochasticController controller;

  @Mock
  @Getter
  private StochasticDto dto;

  @Mock
  @Getter
  private StochasticCreateDto createDto;

  @Getter
  @MockitoBean
  private StochasticService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.mainLine()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.signalLine()).thenReturn(BigDecimal.ONE);
  }
}
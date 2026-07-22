package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.model.Stochastic;
import br.allandemiranda.fx.robot.service.StochasticService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = StochasticController.class)
class StochasticControllerTest extends ChartChartObjectControllerTest<Stochastic, StochasticDto, StochasticCreateDto> {

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
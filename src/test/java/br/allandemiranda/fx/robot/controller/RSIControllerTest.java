package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.RSIDto;
import br.allandemiranda.fx.robot.dto.create.RSICreateDto;
import br.allandemiranda.fx.robot.model.RSI;
import br.allandemiranda.fx.robot.service.RSIService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = RSIController.class)
class RSIControllerTest extends ChartChartObjectControllerTest<RSI, RSIDto, RSICreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private RSIController controller;

  @Mock
  @Getter
  private RSIDto dto;

  @Mock
  @Getter
  private RSICreateDto createDto;

  @Getter
  @MockitoBean
  private RSIService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.rsi()).thenReturn(BigDecimal.ONE);
  }
}
package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.AbstractChartChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.CandlestickDto;
import br.allandemiranda.fx.robot.dto.create.CandlestickCreateDto;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.service.CandlestickService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = CandlestickController.class)
class CandlestickControllerTest extends AbstractChartChartObjectControllerTest<Candlestick, CandlestickDto, CandlestickCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private CandlestickController controller;

  @Mock
  @Getter
  private CandlestickDto dto;

  @Mock
  @Getter
  private CandlestickCreateDto createDto;

  @Getter
  @MockitoBean
  private CandlestickService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.open()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.high()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.low()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.close()).thenReturn(BigDecimal.ONE);
  }
}
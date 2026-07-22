package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.MACDDto;
import br.allandemiranda.fx.robot.dto.create.MACDCreateDto;
import br.allandemiranda.fx.robot.model.MACD;
import br.allandemiranda.fx.robot.service.MACDService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = MACDController.class)
class MACDControllerTest extends ChartChartObjectControllerTest<MACD, MACDDto, MACDCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private MACDController controller;

  @Mock
  @Getter
  private MACDDto dto;

  @Mock
  @Getter
  private MACDCreateDto createDto;

  @Getter
  @MockitoBean
  private MACDService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.mainLine()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.signalLine()).thenReturn(BigDecimal.ONE);
  }
}
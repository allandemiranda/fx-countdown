package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.model.GarchTrading;
import br.allandemiranda.fx.robot.service.GarchTradingService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = GarchTradingController.class)
class GarchTradingControllerTest extends ChartChartObjectControllerTest<GarchTrading, GarchTradingDto, GarchTradingCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private GarchTradingController controller;

  @Mock
  @Getter
  private GarchTradingDto dto;

  @Mock
  @Getter
  private GarchTradingCreateDto createDto;

  @Getter
  @MockitoBean
  private GarchTradingService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.buyOpenPrice()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.buyTpPrice()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.buySlPrice()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.sellOpenPrice()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.sellTpPrice()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.sellSlPrice()).thenReturn(BigDecimal.ONE);
  }
}
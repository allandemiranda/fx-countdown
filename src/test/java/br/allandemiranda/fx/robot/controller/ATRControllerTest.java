package br.allandemiranda.fx.robot.controller;


import br.allandemiranda.fx.robot.controller.contract.AbstractChartChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.ATRDto;
import br.allandemiranda.fx.robot.dto.create.ATRCreateDto;
import br.allandemiranda.fx.robot.model.ATR;
import br.allandemiranda.fx.robot.service.ATRService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = ATRController.class)
class ATRControllerTest extends AbstractChartChartObjectControllerTest<ATR, ATRDto, ATRCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private ATRController controller;

  @Mock
  @Getter
  private ATRDto dto;

  @Mock
  @Getter
  private ATRCreateDto createDto;

  @Getter
  @MockitoBean
  private ATRService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.atr()).thenReturn(BigDecimal.ONE);
  }
}
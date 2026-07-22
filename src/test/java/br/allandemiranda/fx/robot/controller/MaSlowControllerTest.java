package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.MaSlowDto;
import br.allandemiranda.fx.robot.dto.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.model.MaSlow;
import br.allandemiranda.fx.robot.service.MaSlowService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = MaSlowController.class)
class MaSlowControllerTest extends ChartChartObjectControllerTest<MaSlow, MaSlowDto, MaSlowCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private MaSlowController controller;

  @Mock
  @Getter
  private MaSlowDto dto;

  @Mock
  @Getter
  private MaSlowCreateDto createDto;

  @Getter
  @MockitoBean
  private MaSlowService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.ma()).thenReturn(BigDecimal.ONE);
  }
}
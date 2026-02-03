package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.MaSlowDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.model.impl.MaSlow;
import br.allandemiranda.fx.robot.service.impl.MaSlowService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = MaSlowController.class)
class MaSlowControllerTest extends ChartObjectControllerTest<MaSlow, MaSlowDto, MaSlowCreateDto> {

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
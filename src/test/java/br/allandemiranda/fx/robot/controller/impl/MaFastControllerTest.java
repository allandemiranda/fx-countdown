package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.MaFastDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaFastCreateDto;
import br.allandemiranda.fx.robot.model.impl.MaFast;
import br.allandemiranda.fx.robot.service.impl.MaFastService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = MaFastController.class)
class MaFastControllerTest extends ChartObjectControllerTest<MaFast, MaFastDto, MaFastCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private MaFastController controller;

  @Mock
  @Getter
  private MaFastDto dto;

  @Mock
  @Getter
  private MaFastCreateDto createDto;

  @Getter
  @MockitoBean
  private MaFastService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.ma()).thenReturn(BigDecimal.ONE);
  }
}
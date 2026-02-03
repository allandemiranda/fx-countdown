package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.BandsDto;
import br.allandemiranda.fx.robot.dto.impl.create.BandsCreateDto;
import br.allandemiranda.fx.robot.model.impl.Bands;
import br.allandemiranda.fx.robot.service.impl.BandsService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = BandsController.class)
class BandsControllerTest extends ChartObjectControllerTest<Bands, BandsDto, BandsCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private BandsController controller;

  @Mock
  @Getter
  private BandsDto dto;

  @Mock
  @Getter
  private BandsCreateDto createDto;

  @Getter
  @MockitoBean
  private BandsService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.baseLine()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.upperBand()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.lowerBand()).thenReturn(BigDecimal.ONE);
  }
}
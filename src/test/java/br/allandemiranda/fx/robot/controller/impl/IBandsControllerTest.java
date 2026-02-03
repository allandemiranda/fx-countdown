package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.impl.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.impl.IBands;
import br.allandemiranda.fx.robot.service.impl.IBandsService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = IBandsController.class)
class IBandsControllerTest extends InputObjectControllerTest<IBands, IBandsDto, IBandsCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private IBandsController controller;

  @Mock
  @Getter
  private IBandsDto dto;

  @Mock
  @Getter
  private IBandsCreateDto createDto;

  @Getter
  @MockitoBean
  private IBandsService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.period()).thenReturn((short) 1);
    Mockito.when(this.createDto.shift()).thenReturn((short) 1);
    Mockito.when(this.createDto.deviations()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.applyTo()).thenReturn(AppliedPrice.values()[0]);
  }
}
package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.impl.create.IRSICreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.impl.IRSI;
import br.allandemiranda.fx.robot.service.impl.IRSIService;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = IRSIController.class)
class IRSIControllerTest extends InputObjectControllerTest<IRSI, IRSIDto, IRSICreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private IRSIController controller;

  @Mock
  @Getter
  private IRSIDto dto;

  @Mock
  @Getter
  private IRSICreateDto createDto;

  @Getter
  @MockitoBean
  private IRSIService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.period()).thenReturn((short) 1);
    Mockito.when(this.createDto.applyTo()).thenReturn(AppliedPrice.values()[0]);
  }
}
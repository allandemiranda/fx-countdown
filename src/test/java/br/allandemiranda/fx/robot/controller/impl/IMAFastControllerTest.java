package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.IMAFastDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMAFastCreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.impl.IMAFast;
import br.allandemiranda.fx.robot.service.impl.IMAFastService;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = IMAFastController.class)
class IMAFastControllerTest extends InputObjectControllerTest<IMAFast, IMAFastDto, IMAFastCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private IMAFastController controller;

  @Mock
  @Getter
  private IMAFastDto dto;

  @Mock
  @Getter
  private IMAFastCreateDto createDto;

  @Getter
  @MockitoBean
  private IMAFastService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.period()).thenReturn((short) 1);
    Mockito.when(this.createDto.shift()).thenReturn((short) 1);
    Mockito.when(this.createDto.method()).thenReturn(SmoothingMethod.values()[0]);
    Mockito.when(this.createDto.applyTo()).thenReturn(AppliedPrice.values()[0]);
  }
}
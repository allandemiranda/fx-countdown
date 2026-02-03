package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.impl.base.IStochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.IStochasticCreateDto;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.impl.IStochastic;
import br.allandemiranda.fx.robot.service.impl.IStochasticService;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = IStochasticController.class)
class IStochasticControllerTest extends InputObjectControllerTest<IStochastic, IStochasticDto, IStochasticCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private IStochasticController controller;

  @Mock
  @Getter
  private IStochasticDto dto;

  @Mock
  @Getter
  private IStochasticCreateDto createDto;

  @Getter
  @MockitoBean
  private IStochasticService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.kPeriod()).thenReturn((short) 1);
    Mockito.when(this.createDto.dPeriod()).thenReturn((short) 1);
    Mockito.when(this.createDto.slowing()).thenReturn((short) 1);
    Mockito.when(this.createDto.method()).thenReturn(SmoothingMethod.values()[0]);
    Mockito.when(this.createDto.priceField()).thenReturn(PriceField.values()[0]);
  }
}
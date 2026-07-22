package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.AbstractChartChartObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.ADXDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.service.ADXService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = ADXController.class)
class ADXControllerTest extends AbstractChartChartObjectControllerTest<ADX, ADXDto, ADXCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private ADXController controller;

  @Mock
  @Getter
  private ADXDto dto;

  @Mock
  @Getter
  private ADXCreateDto createDto;

  @Getter
  @MockitoBean
  private ADXService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.mainLine()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.plusDiLine()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.minusDiLine()).thenReturn(BigDecimal.ONE);
  }
}
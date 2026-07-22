package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.GarchInputDto;
import br.allandemiranda.fx.robot.dto.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.model.GarchInput;
import br.allandemiranda.fx.robot.service.GarchInputService;
import java.math.BigDecimal;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = GarchInputController.class)
class GarchInputControllerTest extends InputObjectControllerTest<GarchInput, GarchInputDto, GarchInputCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private GarchInputController controller;

  @Mock
  @Getter
  private GarchInputDto dto;

  @Mock
  @Getter
  private GarchInputCreateDto createDto;

  @Getter
  @MockitoBean
  private GarchInputService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.horizon()).thenReturn(1);
    Mockito.when(this.createDto.priceSize()).thenReturn(50);
    Mockito.when(this.createDto.kTP()).thenReturn(BigDecimal.ONE);
    Mockito.when(this.createDto.kSL()).thenReturn(BigDecimal.ONE);
  }
}
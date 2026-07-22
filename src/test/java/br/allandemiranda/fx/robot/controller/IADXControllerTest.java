package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.IADXDto;
import br.allandemiranda.fx.robot.dto.create.IADXCreateDto;
import br.allandemiranda.fx.robot.model.IADX;
import br.allandemiranda.fx.robot.service.IADXService;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = IADXController.class)
class IADXControllerTest extends InputObjectControllerTest<IADX, IADXDto, IADXCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private IADXController controller;

  @Mock
  @Getter
  private IADXDto dto;

  @Mock
  @Getter
  private IADXCreateDto createDto;

  @Getter
  @MockitoBean
  private IADXService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.period()).thenReturn((short) 1);
  }
}
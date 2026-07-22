package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.IATRDto;
import br.allandemiranda.fx.robot.dto.create.IATRCreateDto;
import br.allandemiranda.fx.robot.model.IATR;
import br.allandemiranda.fx.robot.service.IATRService;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = IATRController.class)
class IATRControllerTest extends InputObjectControllerTest<IATR, IATRDto, IATRCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private IATRController controller;

  @Mock
  @Getter
  private IATRDto dto;

  @Mock
  @Getter
  private IATRCreateDto createDto;

  @Getter
  @MockitoBean
  private IATRService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.period()).thenReturn((short) 1);
  }
}
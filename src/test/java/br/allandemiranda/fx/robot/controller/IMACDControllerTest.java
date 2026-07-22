package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.IMACDDto;
import br.allandemiranda.fx.robot.dto.create.IMACDCreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.IMACD;
import br.allandemiranda.fx.robot.service.IMACDService;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = IMACDController.class)
class IMACDControllerTest extends InputObjectControllerTest<IMACD, IMACDDto, IMACDCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private IMACDController controller;

  @Mock
  @Getter
  private IMACDDto dto;

  @Mock
  @Getter
  private IMACDCreateDto createDto;

  @Getter
  @MockitoBean
  private IMACDService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.fastEma()).thenReturn((short) 1);
    Mockito.when(this.createDto.slowEma()).thenReturn((short) 1);
    Mockito.when(this.createDto.macdSma()).thenReturn((short) 1);
    Mockito.when(this.createDto.applyTo()).thenReturn(AppliedPrice.values()[0]);
  }
}
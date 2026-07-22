package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.model.MLInput;
import br.allandemiranda.fx.robot.service.MLInputService;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = MLInputController.class)
class MLInputControllerTest extends InputObjectControllerTest<MLInput, MLInputDto, MLInputCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private MLInputController controller;

  @Mock
  @Getter
  private MLInputDto dto;

  @Mock
  @Getter
  private MLInputCreateDto createDto;

  @Getter
  @MockitoBean
  private MLInputService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.chartObjectNum()).thenReturn(1);
    Mockito.when(this.createDto.maxDepth()).thenReturn(1);
    Mockito.when(this.createDto.eta()).thenReturn(1f);
    Mockito.when(this.createDto.subsample()).thenReturn(1f);
    Mockito.when(this.createDto.colSampleByTree()).thenReturn(1f);
    Mockito.when(this.createDto.minChildWeight()).thenReturn(1);
    Mockito.when(this.createDto.lambda()).thenReturn(1f);
    Mockito.when(this.createDto.alpha()).thenReturn(1f);
  }
}
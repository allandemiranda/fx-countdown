package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectControllerTest;
import br.allandemiranda.fx.robot.dto.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.model.Dashboard;
import br.allandemiranda.fx.robot.service.DashboardService;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = DashboardController.class)
class DashboardControllerTest extends InputObjectControllerTest<Dashboard, DashboardDto, DashboardCreateDto> {

  @Getter
  @Autowired
  private WebTestClient webTestClient;

  @Getter
  @Autowired
  private DashboardController controller;

  @Mock
  @Getter
  private DashboardDto dto;

  @Mock
  @Getter
  private DashboardCreateDto createDto;

  @Getter
  @MockitoBean
  private DashboardService service;

  @Override
  protected void setupCreateDto() {
    Mockito.when(this.createDto.startScope()).thenReturn(OffsetDateTime.now(ZoneOffset.UTC));
    Mockito.when(this.createDto.endScope()).thenReturn(OffsetDateTime.now(ZoneOffset.UTC));
  }
}
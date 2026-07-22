package br.allandemiranda.fx.robot.controller.contract;

import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@Getter(AccessLevel.PROTECTED)
@ExtendWith(MockitoExtension.class)
public abstract class EssentialServicesTest {

  @MockitoBean
  private SymbolService symbolService;

  @MockitoBean
  private ChartService chartService;

}
package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.GarchInputDto;
import br.allandemiranda.fx.robot.dto.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.model.GarchInput;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.GarchInputService;
import br.allandemiranda.fx.robot.service.SymbolService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{period}/garch-inputs")
public class GarchInputController implements InputObjectController<GarchInput, GarchInputDto, GarchInputCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final GarchInputService service;

  @Override
  public String getChartObjectName() {
    return "garch input";
  }

}

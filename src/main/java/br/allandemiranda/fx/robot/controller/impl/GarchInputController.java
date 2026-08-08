package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.GarchInputDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.model.impl.GarchInput;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.GarchInputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{timeframe}/garch_inputs")
public class GarchInputController implements InputObjectController<GarchInput, GarchInputDto, GarchInputCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final GarchInputService service;

  @Override
  public String getInputObjectName() {
    return "garch input";
  }

}

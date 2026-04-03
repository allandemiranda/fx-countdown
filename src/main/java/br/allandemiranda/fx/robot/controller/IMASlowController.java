package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.IMASlowDto;
import br.allandemiranda.fx.robot.dto.create.IMASlowCreateDto;
import br.allandemiranda.fx.robot.model.IMASlow;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.IMASlowService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/imas-slow")
public class IMASlowController implements InputObjectController<IMASlow, IMASlowDto, IMASlowCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IMASlowService service;

  @Override
  public String getChartObjectName() {
    return "iMA slow";
  }

}

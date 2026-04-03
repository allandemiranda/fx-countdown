package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.IStochasticDto;
import br.allandemiranda.fx.robot.dto.create.IStochasticCreateDto;
import br.allandemiranda.fx.robot.model.IStochastic;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.IStochasticService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/istochastics")
public class IStochasticController implements InputObjectController<IStochastic, IStochasticDto, IStochasticCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IStochasticService service;

  @Override
  public String getChartObjectName() {
    return "iStochastic";
  }

}

package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.model.Stochastic;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.StochasticService;
import br.allandemiranda.fx.robot.service.SymbolService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{name}/timeframes/{period}/stochastics")
public class StochasticController implements ChartObjectController<Stochastic, StochasticDto, StochasticCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final StochasticService service;

  @Override
  public String getChartObjectName() {
    return "Stochastic";
  }

}
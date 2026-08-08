package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.model.impl.Stochastic;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.StochasticService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{name}/timeframes/{timeframe}/stochastics")
public class StochasticController implements ChartObjectController<Stochastic, StochasticDto, StochasticCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final StochasticService service;

  @Override
  public String getChartObjectName() {
    return "Stochastic";
  }

}
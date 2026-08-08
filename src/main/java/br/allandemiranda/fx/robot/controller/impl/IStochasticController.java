package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.IStochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.IStochasticCreateDto;
import br.allandemiranda.fx.robot.model.impl.IStochastic;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.IStochasticService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{timeframe}/i_stochastics")
public class IStochasticController implements InputObjectController<IStochastic, IStochasticDto, IStochasticCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IStochasticService service;

  @Override
  public String getInputObjectName() {
    return "i_Stochastic";
  }

}

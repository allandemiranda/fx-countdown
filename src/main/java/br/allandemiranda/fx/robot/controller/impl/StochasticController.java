package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.IndicatorController;
import br.allandemiranda.fx.robot.dto.impl.indicator.StochasticCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.StochasticDto;
import br.allandemiranda.fx.robot.model.impl.indicator.Stochastic;
import br.allandemiranda.fx.robot.service.DashboardService;
import br.allandemiranda.fx.robot.service.impl.indicator.StochasticService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("dashboards/{dashboardId}/stochastics")
public class StochasticController implements IndicatorController<Stochastic, StochasticDto, StochasticCreateDto> {

  private final StochasticService service;
  private final DashboardService dashboardService;

}
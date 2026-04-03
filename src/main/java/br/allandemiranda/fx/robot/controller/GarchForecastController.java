package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.create.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.model.GarchForecast;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.GarchForecastService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/garch-forecasts")
public class GarchForecastController implements ChartObjectController<GarchForecast, GarchForecastDto, GarchForecastCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final GarchForecastService service;

  @Override
  public String getChartObjectName() {
    return "garch forecasts";
  }

}
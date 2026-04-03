package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.model.GarchTrading;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.GarchTradingService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/garch-tradings")
public class GarchTradingController implements ChartObjectController<GarchTrading, GarchTradingDto, GarchTradingCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final GarchTradingService service;

  @Override
  public String getChartObjectName() {
    return "garch trading";
  }

}
package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.RSIDto;
import br.allandemiranda.fx.robot.dto.impl.create.RSICreateDto;
import br.allandemiranda.fx.robot.model.impl.RSI;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.RSIService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{name}/timeframes/{timeframe}/rsis")
public class RSIController implements ChartObjectController<RSI, RSIDto, RSICreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final RSIService service;

  @Override
  public String getChartObjectName() {
    return "RSI";
  }

}
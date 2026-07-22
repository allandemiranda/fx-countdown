package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.RSIDto;
import br.allandemiranda.fx.robot.dto.create.RSICreateDto;
import br.allandemiranda.fx.robot.model.RSI;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.RSIService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/rsis")
public class RSIController implements ChartObjectController<RSI, RSIDto, RSICreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final RSIService service;

  @Override
  public String getChartObjectName() {
    return "RSI";
  }

}
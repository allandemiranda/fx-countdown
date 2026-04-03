package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.MACDDto;
import br.allandemiranda.fx.robot.dto.create.MACDCreateDto;
import br.allandemiranda.fx.robot.model.MACD;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.MACDService;
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
public class RSIController implements ChartObjectController<MACD, MACDDto, MACDCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final MACDService service;

  @Override
  public String getChartObjectName() {
    return "RSI";
  }

}
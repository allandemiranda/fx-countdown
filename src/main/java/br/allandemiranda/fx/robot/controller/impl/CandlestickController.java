package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.CandlestickDto;
import br.allandemiranda.fx.robot.dto.impl.create.CandlestickCreateDto;
import br.allandemiranda.fx.robot.model.impl.Candlestick;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.CandlestickService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{name}/timeframes/{period}/candlesticks")
public class CandlestickController implements ChartObjectController<Candlestick, CandlestickDto, CandlestickCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final CandlestickService service;

  @Override
  public String getChartObjectName() {
    return "candlestick";
  }

}
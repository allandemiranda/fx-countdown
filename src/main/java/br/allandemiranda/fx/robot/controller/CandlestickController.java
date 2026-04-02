package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.CandlestickDto;
import br.allandemiranda.fx.robot.dto.create.CandlestickCreateDto;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.repository.CandlestickRepository;
import br.allandemiranda.fx.robot.service.CandlestickService;
import br.allandemiranda.fx.robot.service.ChartService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/candlesticks")
public final class CandlestickController implements ChartObjectController<Candlestick, CandlestickDto, CandlestickCreateDto, CandlestickRepository, CandlestickService> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final CandlestickService service;

  @Override
  public String getChartObjectName() {
    return "candlestick";
  }

}
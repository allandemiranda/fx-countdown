package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.MACDDto;
import br.allandemiranda.fx.robot.dto.impl.create.MACDCreateDto;
import br.allandemiranda.fx.robot.model.impl.MACD;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.MACDService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{name}/timeframes/{period}/macds")
public class MACDController implements ChartObjectController<MACD, MACDDto, MACDCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final MACDService service;

  @Override
  public String getChartObjectName() {
    return "MACD";
  }

}
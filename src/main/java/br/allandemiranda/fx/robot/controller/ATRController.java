package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.ATRDto;
import br.allandemiranda.fx.robot.dto.create.ATRCreateDto;
import br.allandemiranda.fx.robot.model.ATR;
import br.allandemiranda.fx.robot.service.ATRService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/atrs")
public class ATRController implements ChartObjectController<ATR, ATRDto, ATRCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final ATRService service;

  @Override
  public String getChartObjectName() {
    return "ATR";
  }

}
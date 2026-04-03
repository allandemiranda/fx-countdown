package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.create.IRSICreateDto;
import br.allandemiranda.fx.robot.model.IRSI;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.IRSIService;
import br.allandemiranda.fx.robot.service.SymbolService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{period}/irsis")
public class IRSIController implements InputObjectController<IRSI, IRSIDto, IRSICreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IRSIService service;

  @Override
  public String getChartObjectName() {
    return "iRSI";
  }

}

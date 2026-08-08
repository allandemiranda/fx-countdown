package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.impl.create.IRSICreateDto;
import br.allandemiranda.fx.robot.model.impl.IRSI;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.IRSIService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{timeframe}/i_rsis")
public class IRSIController implements InputObjectController<IRSI, IRSIDto, IRSICreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IRSIService service;

  @Override
  public String getInputObjectName() {
    return "i_RSI";
  }

}

package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.MaSlowDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.model.impl.MaSlow;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.MaSlowService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{name}/timeframes/{timeframe}/ma_slows")
public class MaSlowController implements ChartObjectController<MaSlow, MaSlowDto, MaSlowCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final MaSlowService service;

  @Override
  public String getChartObjectName() {
    return "MA slow";
  }

}
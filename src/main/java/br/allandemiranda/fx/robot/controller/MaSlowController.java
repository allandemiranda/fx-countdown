package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.MaSlowDto;
import br.allandemiranda.fx.robot.dto.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.model.MaSlow;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.MaSlowService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/ma-slows")
public class MaSlowController implements ChartObjectController<MaSlow, MaSlowDto, MaSlowCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final MaSlowService service;

  @Override
  public String getChartObjectName() {
    return "MA slow";
  }

}
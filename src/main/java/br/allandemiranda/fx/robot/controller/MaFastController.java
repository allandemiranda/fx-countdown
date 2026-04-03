package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.MaFastDto;
import br.allandemiranda.fx.robot.dto.create.MaFastCreateDto;
import br.allandemiranda.fx.robot.model.MaFast;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.MaFastService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/ma-fasts")
public class MaFastController implements ChartObjectController<MaFast, MaFastDto, MaFastCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final MaFastService service;

  @Override
  public String getChartObjectName() {
    return "ma fast";
  }

}
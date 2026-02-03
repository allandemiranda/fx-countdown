package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.MaFastDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaFastCreateDto;
import br.allandemiranda.fx.robot.model.impl.MaFast;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.MaFastService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{name}/timeframes/{period}/ma_fasts")
public class MaFastController implements ChartObjectController<MaFast, MaFastDto, MaFastCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final MaFastService service;

  @Override
  public String getChartObjectName() {
    return "MA fast";
  }

}
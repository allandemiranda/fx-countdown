package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.IMAFastDto;
import br.allandemiranda.fx.robot.dto.create.IMAFastCreateDto;
import br.allandemiranda.fx.robot.model.IMAFast;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.IMAFastService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/imas-fast")
public class IMAFastController implements InputObjectController<IMAFast, IMAFastDto, IMAFastCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IMAFastService service;

  @Override
  public String getChartObjectName() {
    return "iMA fast";
  }

}

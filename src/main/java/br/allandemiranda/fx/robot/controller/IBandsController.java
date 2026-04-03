package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.model.IBands;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.IBandsService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/ibandss")
public class IBandsController implements InputObjectController<IBands, IBandsDto, IBandsCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IBandsService service;

  @Override
  public String getChartObjectName() {
    return "iBands";
  }

}

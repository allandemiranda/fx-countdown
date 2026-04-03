package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.IADXDto;
import br.allandemiranda.fx.robot.dto.create.IADXCreateDto;
import br.allandemiranda.fx.robot.model.IADX;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.IADXService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/iadxs")
public class IADXController implements InputObjectController<IADX, IADXDto, IADXCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IADXService service;

  @Override
  public String getChartObjectName() {
    return "iADX";
  }

}

package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.IATRDto;
import br.allandemiranda.fx.robot.dto.create.IATRCreateDto;
import br.allandemiranda.fx.robot.model.IATR;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.IATRService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/iatrs")
public class IATRController implements InputObjectController<IATR, IATRDto, IATRCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IATRService service;

  @Override
  public String getChartObjectName() {
    return "iATR";
  }

}

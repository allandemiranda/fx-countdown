package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.IATRDto;
import br.allandemiranda.fx.robot.dto.impl.create.IATRCreateDto;
import br.allandemiranda.fx.robot.model.impl.IATR;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.IATRService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{timeframe}/i_atrs")
public class IATRController implements InputObjectController<IATR, IATRDto, IATRCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IATRService service;

  @Override
  public String getInputObjectName() {
    return "i_ATR";
  }

}

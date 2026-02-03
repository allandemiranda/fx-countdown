package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.IADXDto;
import br.allandemiranda.fx.robot.dto.impl.create.IADXCreateDto;
import br.allandemiranda.fx.robot.model.impl.IADX;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.IADXService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{period}/i_adxs")
public class IADXController implements InputObjectController<IADX, IADXDto, IADXCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IADXService service;

  @Override
  public String getInputObjectName() {
    return "i_ADX";
  }

}

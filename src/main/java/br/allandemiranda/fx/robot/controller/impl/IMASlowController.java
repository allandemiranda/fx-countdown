package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.IMASlowDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMASlowCreateDto;
import br.allandemiranda.fx.robot.model.impl.IMASlow;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.IMASlowService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{timeframe}/i_ma_slows")
public class IMASlowController implements InputObjectController<IMASlow, IMASlowDto, IMASlowCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IMASlowService service;

  @Override
  public String getInputObjectName() {
    return "i_MA slow";
  }

}

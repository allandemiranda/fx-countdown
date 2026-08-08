package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.IMAFastDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMAFastCreateDto;
import br.allandemiranda.fx.robot.model.impl.IMAFast;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.IMAFastService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{timeframe}/i_ma_fasts")
public class IMAFastController implements InputObjectController<IMAFast, IMAFastDto, IMAFastCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IMAFastService service;

  @Override
  public String getInputObjectName() {
    return "i_MA fast";
  }

}

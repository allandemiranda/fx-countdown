package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.IMACDDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMACDCreateDto;
import br.allandemiranda.fx.robot.model.impl.IMACD;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.IMACDService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{period}/i_macds")
public class IMACDController implements InputObjectController<IMACD, IMACDDto, IMACDCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IMACDService service;

  @Override
  public String getInputObjectName() {
    return "i_MACD";
  }

}

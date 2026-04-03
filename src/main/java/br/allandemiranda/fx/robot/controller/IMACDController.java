package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.IMACDDto;
import br.allandemiranda.fx.robot.dto.create.IMACDCreateDto;
import br.allandemiranda.fx.robot.model.IMACD;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.IMACDService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/imacds")
public class IMACDController implements InputObjectController<IMACD, IMACDDto, IMACDCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IMACDService service;

  @Override
  public String getChartObjectName() {
    return "iMACD";
  }

}

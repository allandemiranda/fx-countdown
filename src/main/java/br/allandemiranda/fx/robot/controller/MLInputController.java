package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.model.MLInput;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.MLInputService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/ml-inputs")
public class MLInputController implements InputObjectController<MLInput, MLInputDto, MLInputCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final MLInputService service;

  @Override
  public String getChartObjectName() {
    return "ML input";
  }

}

package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.impl.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.model.impl.MLInput;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.MLInputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{timeframe}/ml_inputs")
public class MLInputController implements InputObjectController<MLInput, MLInputDto, MLInputCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final MLInputService service;

  @Override
  public String getInputObjectName() {
    return "ML input";
  }

}

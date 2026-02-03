package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.impl.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.model.impl.IBands;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.IBandsService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{period}/i_bandss")
public class IBandsController implements InputObjectController<IBands, IBandsDto, IBandsCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final IBandsService service;

  @Override
  public String getInputObjectName() {
    return "i_Bands";
  }

}

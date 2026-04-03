package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.ADXDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.service.ADXService;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{name}/timeframes/{period}/adxs")
public class ADXController implements ChartObjectController<ADX, ADXDto, ADXCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final ADXService service;

  @Override
  public String getChartObjectName() {
    return "ADX";
  }

}
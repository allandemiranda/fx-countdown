package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.ChartObjectController;
import br.allandemiranda.fx.robot.dto.impl.base.BandsDto;
import br.allandemiranda.fx.robot.dto.impl.create.BandsCreateDto;
import br.allandemiranda.fx.robot.model.impl.Bands;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.BandsService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{name}/timeframes/{period}/bandss")
public class BandsController implements ChartObjectController<Bands, BandsDto, BandsCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final BandsService service;

  @Override
  public String getChartObjectName() {
    return "Bands";
  }

}
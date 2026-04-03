package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.ChartObjectController;
import br.allandemiranda.fx.robot.dto.base.BandsDto;
import br.allandemiranda.fx.robot.dto.create.BandsCreateDto;
import br.allandemiranda.fx.robot.model.Bands;
import br.allandemiranda.fx.robot.service.BandsService;
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
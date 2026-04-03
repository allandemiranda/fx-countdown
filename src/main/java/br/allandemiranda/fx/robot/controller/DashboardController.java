package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.contract.InputObjectController;
import br.allandemiranda.fx.robot.dto.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.model.Dashboard;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.DashboardService;
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
@RequestMapping("symbols/{name}/timeframes/{period}/dashboards")
public class DashboardController implements InputObjectController<Dashboard, DashboardDto, DashboardCreateDto> {

  private final SymbolService symbolService;
  private final ChartService chartService;
  private final DashboardService service;

  @Override
  public String getChartObjectName() {
    return "dashboard";
  }

}

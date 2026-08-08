package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.IndicatorController;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDDto;
import br.allandemiranda.fx.robot.model.impl.indicator.MACD;
import br.allandemiranda.fx.robot.service.DashboardService;
import br.allandemiranda.fx.robot.service.impl.indicator.MACDService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("dashboards/{dashboardId}/macds")
public class MACDController implements IndicatorController<MACD, MACDDto, MACDCreateDto> {

  private final MACDService service;
  private final DashboardService dashboardService;

}
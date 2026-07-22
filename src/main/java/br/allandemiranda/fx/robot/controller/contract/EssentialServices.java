package br.allandemiranda.fx.robot.controller.contract;

import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;

public interface EssentialServices {

  SymbolService getSymbolService();

  ChartService getChartService();

}

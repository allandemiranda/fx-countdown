package br.allandemiranda.fx.robot.controller.indicator.impl;

import br.allandemiranda.fx.robot.controller.indicator.IndicatorController;
import br.allandemiranda.fx.robot.dto.indicator.StochasticDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.StochasticCreateDto;
import br.allandemiranda.fx.robot.model.indicator.impl.StochasticEntry;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.impl.StochasticService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors/{expertAdvisorName}/stochastics")
public class StochasticController implements IndicatorController<StochasticEntry, StochasticDto, StochasticCreateDto> {

  private final ExpertAdvisorService expertAdvisorService;
  private final StochasticService service;
  private final SymbolService symbolService;

}

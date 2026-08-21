package br.allandemiranda.fx.robot.controller.indicator.impl;

import br.allandemiranda.fx.robot.controller.indicator.IndicatorController;
import br.allandemiranda.fx.robot.dto.indicator.RSIDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.RSICreateDto;
import br.allandemiranda.fx.robot.model.indicator.impl.RSIEntry;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.impl.RSIService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors/{expertAdvisorName}/rsis")
public class RSIController implements IndicatorController<RSIEntry, RSIDto, RSICreateDto> {

  private final ExpertAdvisorService expertAdvisorService;
  private final RSIService service;
  private final SymbolService symbolService;

}

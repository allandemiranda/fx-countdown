package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.IndicatorController;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSICreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSIDto;
import br.allandemiranda.fx.robot.model.impl.indicator.RSI;
import br.allandemiranda.fx.robot.service.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.indicator.RSIService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("expert_advisors/{expertAdvisorName}/rsis")
public class RSIController implements IndicatorController<RSI, RSIDto, RSICreateDto> {

  private final RSIService service;
  private final SymbolService symbolService;
  private final ExpertAdvisorService expertAdvisorService;

}
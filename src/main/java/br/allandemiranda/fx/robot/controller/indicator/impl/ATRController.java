package br.allandemiranda.fx.robot.controller.indicator.impl;

import br.allandemiranda.fx.robot.controller.indicator.IndicatorController;
import br.allandemiranda.fx.robot.dto.indicator.ATRDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.ATRCreateDto;
import br.allandemiranda.fx.robot.model.indicator.impl.ATREntry;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.impl.ATRService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors/{expertAdvisorName}/atrs")
public class ATRController implements IndicatorController<ATREntry, ATRDto, ATRCreateDto> {

  private final ExpertAdvisorService expertAdvisorService;
  private final ATRService service;
  private final SymbolService symbolService;

}

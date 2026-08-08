package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.IndicatorController;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATRCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATRDto;
import br.allandemiranda.fx.robot.model.impl.indicator.ATR;
import br.allandemiranda.fx.robot.service.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.indicator.ATRService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("expert_advisors/{expertAdvisorName}/atrs")
public class ATRController implements IndicatorController<ATR, ATRDto, ATRCreateDto> {

  private final ATRService service;
  private final SymbolService symbolService;
  private final ExpertAdvisorService expertAdvisorService;

}
package br.allandemiranda.fx.robot.controller.indicator.impl;

import br.allandemiranda.fx.robot.controller.indicator.IndicatorController;
import br.allandemiranda.fx.robot.dto.indicator.MaSlowDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.MaSlowCreateDto;
import br.allandemiranda.fx.robot.model.indicator.impl.MaSlowEntry;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.impl.MaSlowService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors/{expertAdvisorName}/ma_slows")
public class MaSlowController implements IndicatorController<MaSlowEntry, MaSlowDto, MaSlowCreateDto> {

  private final ExpertAdvisorService expertAdvisorService;
  private final MaSlowService service;
  private final SymbolService symbolService;

}

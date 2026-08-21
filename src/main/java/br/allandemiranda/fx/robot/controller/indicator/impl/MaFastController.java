package br.allandemiranda.fx.robot.controller.indicator.impl;

import br.allandemiranda.fx.robot.controller.indicator.IndicatorController;
import br.allandemiranda.fx.robot.dto.indicator.MaFastDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.MaFastCreateDto;
import br.allandemiranda.fx.robot.model.indicator.impl.MaFastEntry;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.impl.MaFastService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors/{expertAdvisorName}/ma_fasts")
public class MaFastController implements IndicatorController<MaFastEntry, MaFastDto, MaFastCreateDto> {

  private final ExpertAdvisorService expertAdvisorService;
  private final MaFastService service;
  private final SymbolService symbolService;

}

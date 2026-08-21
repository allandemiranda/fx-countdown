package br.allandemiranda.fx.robot.controller.indicator.impl;

import br.allandemiranda.fx.robot.controller.indicator.IndicatorController;
import br.allandemiranda.fx.robot.dto.indicator.BandsDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.BandsCreateDto;
import br.allandemiranda.fx.robot.model.indicator.impl.BandsEntry;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.impl.BandsService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors/{expertAdvisorName}/bandss")
public class BandsController implements IndicatorController<BandsEntry, BandsDto, BandsCreateDto> {

  private final ExpertAdvisorService expertAdvisorService;
  private final BandsService service;
  private final SymbolService symbolService;

}

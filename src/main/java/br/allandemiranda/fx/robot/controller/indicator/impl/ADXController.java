package br.allandemiranda.fx.robot.controller.indicator.impl;

import br.allandemiranda.fx.robot.controller.indicator.IndicatorController;
import br.allandemiranda.fx.robot.dto.indicator.ADXDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.ADXCreateDto;
import br.allandemiranda.fx.robot.model.indicator.impl.ADXEntry;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.impl.ADXService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors/{expertAdvisorName}/adxs")
public class ADXController implements IndicatorController<ADXEntry, ADXDto, ADXCreateDto> {

  private final ExpertAdvisorService expertAdvisorService;
  private final ADXService service;
  private final SymbolService symbolService;

}

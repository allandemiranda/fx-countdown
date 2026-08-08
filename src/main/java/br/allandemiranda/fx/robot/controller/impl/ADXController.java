package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.IndicatorController;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXDto;
import br.allandemiranda.fx.robot.model.impl.indicator.ADX;
import br.allandemiranda.fx.robot.service.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.indicator.ADXService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("expert_advisors/{expertAdvisorName}/adxs")
public class ADXController implements IndicatorController<ADX, ADXDto, ADXCreateDto> {

  private final SymbolService symbolService;
  private final ExpertAdvisorService expertAdvisorService;
  private final ADXService service;

}
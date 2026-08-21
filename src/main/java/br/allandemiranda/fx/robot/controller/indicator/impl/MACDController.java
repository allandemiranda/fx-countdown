package br.allandemiranda.fx.robot.controller.indicator.impl;

import br.allandemiranda.fx.robot.controller.indicator.IndicatorController;
import br.allandemiranda.fx.robot.dto.indicator.MACDDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.MACDCreateDto;
import br.allandemiranda.fx.robot.model.indicator.impl.MACDEntry;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.impl.MACDService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors/{expertAdvisorName}/macds")
public class MACDController implements IndicatorController<MACDEntry, MACDDto, MACDCreateDto> {

  private final ExpertAdvisorService expertAdvisorService;
  private final MACDService service;
  private final SymbolService symbolService;

}

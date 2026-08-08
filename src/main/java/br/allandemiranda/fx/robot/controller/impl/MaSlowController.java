package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.IndicatorController;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlowDto;
import br.allandemiranda.fx.robot.model.impl.indicator.MaSlow;
import br.allandemiranda.fx.robot.service.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.indicator.MaSlowService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("expert_advisors/{expertAdvisorName}/ma_slows")
public class MaSlowController implements IndicatorController<MaSlow, MaSlowDto, MaSlowCreateDto> {

  private final MaSlowService service;
  private final SymbolService symbolService;
  private final ExpertAdvisorService expertAdvisorService;

}
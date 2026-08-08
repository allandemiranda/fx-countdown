package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.IndicatorController;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFastCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFastDto;
import br.allandemiranda.fx.robot.model.impl.indicator.MaFast;
import br.allandemiranda.fx.robot.service.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.indicator.MaFastService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter
@RestController
@Validated
@RequestMapping("expert_advisors/{expertAdvisorName}/ma_fasts")
public class MaFastController implements IndicatorController<MaFast, MaFastDto, MaFastCreateDto> {

  private final MaFastService service;
  private final SymbolService symbolService;
  private final ExpertAdvisorService expertAdvisorService;

}
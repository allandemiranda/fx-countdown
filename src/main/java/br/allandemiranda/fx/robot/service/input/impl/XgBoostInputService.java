package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.XgBoostInputDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.XgBoostInputCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.XgBoostInputMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.XgBoostInputEntry;
import br.allandemiranda.fx.robot.repository.input.impl.XgBoostInputRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class XgBoostInputService implements InputService<XgBoostInputEntry, XgBoostInputDto, XgBoostInputCreateDto> {

  private final XgBoostInputMapper mapper;
  private final XgBoostInputRepository repository;

  @Override
  public XgBoostInputCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.xgBoost();
  }
}

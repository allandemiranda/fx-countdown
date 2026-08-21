package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.IMASlowDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IMASlowCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.IMASlowMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.IMASlowEntry;
import br.allandemiranda.fx.robot.repository.input.impl.IMASlowRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class IMASlowService implements InputService<IMASlowEntry, IMASlowDto, IMASlowCreateDto> {

  private final IMASlowMapper mapper;
  private final IMASlowRepository repository;

  @Override
  public IMASlowCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.imaSlow();
  }
}

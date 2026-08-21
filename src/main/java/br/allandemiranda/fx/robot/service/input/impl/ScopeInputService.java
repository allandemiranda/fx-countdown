package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.ScopeInputDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.ScopeInputMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.ScopeInputEntry;
import br.allandemiranda.fx.robot.repository.input.impl.ScopeInputRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class ScopeInputService implements InputService<ScopeInputEntry, ScopeInputDto, ScopeInputCreateDto> {

  private final ScopeInputMapper mapper;
  private final ScopeInputRepository repository;

  @Override
  public ScopeInputCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.scope();
  }
}

package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.GarchInputDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.GarchInputCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.GarchInputMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.GarchInputEntry;
import br.allandemiranda.fx.robot.repository.input.impl.GarchInputRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class GarchInputService implements InputService<GarchInputEntry, GarchInputDto, GarchInputCreateDto> {

  private final GarchInputMapper mapper;
  private final GarchInputRepository repository;

  @Override
  public GarchInputCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.garch();
  }
}

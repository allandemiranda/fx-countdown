package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.IMAFastDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IMAFastCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.IMAFastMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.IMAFastEntry;
import br.allandemiranda.fx.robot.repository.input.impl.IMAFastRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class IMAFastService implements InputService<IMAFastEntry, IMAFastDto, IMAFastCreateDto> {

  private final IMAFastMapper mapper;
  private final IMAFastRepository repository;

  @Override
  public IMAFastCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.imaFast();
  }
}

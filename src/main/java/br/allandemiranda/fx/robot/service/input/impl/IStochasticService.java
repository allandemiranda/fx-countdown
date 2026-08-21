package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.IStochasticDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IStochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.IStochasticMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.IStochasticEntry;
import br.allandemiranda.fx.robot.repository.input.impl.IStochasticRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class IStochasticService implements InputService<IStochasticEntry, IStochasticDto, IStochasticCreateDto> {

  private final IStochasticMapper mapper;
  private final IStochasticRepository repository;

  @Override
  public IStochasticCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.iStochastic();
  }
}

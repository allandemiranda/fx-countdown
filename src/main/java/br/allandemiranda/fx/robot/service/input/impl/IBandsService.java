package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.IBandsDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IBandsCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.IBandsMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.IBandsEntry;
import br.allandemiranda.fx.robot.repository.input.impl.IBandsRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class IBandsService implements InputService<IBandsEntry, IBandsDto, IBandsCreateDto> {

  private final IBandsMapper mapper;
  private final IBandsRepository repository;

  @Override
  public IBandsCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.ibands();
  }
}

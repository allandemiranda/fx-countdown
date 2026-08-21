package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.IATRDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IATRCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.IATRMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.IATREntry;
import br.allandemiranda.fx.robot.repository.input.impl.IATRRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class IATRService implements InputService<IATREntry, IATRDto, IATRCreateDto> {

  private final IATRMapper mapper;
  private final IATRRepository repository;

  @Override
  public IATRCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.iatr();
  }
}

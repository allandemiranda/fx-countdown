package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.IADXDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IADXCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.IADXMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.IADXEntry;
import br.allandemiranda.fx.robot.repository.input.impl.IADXRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class IADXService implements InputService<IADXEntry, IADXDto, IADXCreateDto> {

  private final IADXMapper mapper;
  private final IADXRepository repository;

  @Override
  public IADXCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.iadx();
  }
}

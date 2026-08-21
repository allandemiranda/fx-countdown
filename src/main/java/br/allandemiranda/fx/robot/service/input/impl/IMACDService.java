package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.IMACDDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IMACDCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.IMACDMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.IMACDEntry;
import br.allandemiranda.fx.robot.repository.input.impl.IMACDRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class IMACDService implements InputService<IMACDEntry, IMACDDto, IMACDCreateDto> {

  private final IMACDMapper mapper;
  private final IMACDRepository repository;

  @Override
  public IMACDCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.imacd();
  }
}

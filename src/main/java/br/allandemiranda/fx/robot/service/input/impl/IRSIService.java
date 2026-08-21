package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.IRSIDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IRSICreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.IRSIMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.IRSIEntry;
import br.allandemiranda.fx.robot.repository.input.impl.IRSIRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class IRSIService implements InputService<IRSIEntry, IRSIDto, IRSICreateDto> {

  private final IRSIMapper mapper;
  private final IRSIRepository repository;

  @Override
  public IRSICreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.irsi();
  }
}

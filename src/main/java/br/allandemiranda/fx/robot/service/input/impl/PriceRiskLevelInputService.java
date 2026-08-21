package br.allandemiranda.fx.robot.service.input.impl;

import br.allandemiranda.fx.robot.dto.input.PriceRiskLevelInputDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.PriceRiskLevelInputCreateDto;
import br.allandemiranda.fx.robot.mapper.input.impl.PriceRiskLevelInputMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.impl.PriceRiskLevelInputEntry;
import br.allandemiranda.fx.robot.repository.input.impl.PriceRiskLevelInputRepository;
import br.allandemiranda.fx.robot.service.input.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class PriceRiskLevelInputService implements InputService<PriceRiskLevelInputEntry, PriceRiskLevelInputDto, PriceRiskLevelInputCreateDto> {

  private final PriceRiskLevelInputMapper mapper;
  private final PriceRiskLevelInputRepository repository;

  @Override
  public PriceRiskLevelInputCreateDto getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs) {
    return expertAdvisorInputs.priceRiskLevel();
  }
}

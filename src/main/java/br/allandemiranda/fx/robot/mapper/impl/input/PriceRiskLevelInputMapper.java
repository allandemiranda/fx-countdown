package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.PriceRiskLevelInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.PriceRiskLevelInputDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.PriceRiskLevelInput;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class PriceRiskLevelInputMapper implements InputMapper<PriceRiskLevelInput, PriceRiskLevelInputDto, PriceRiskLevelInputCreateDto> {

  @Override
  public PriceRiskLevelInputDto toDto(ExpertAdvisorDto expertAdvisorDto, PriceRiskLevelInput model) {
    return new PriceRiskLevelInputDto(model.id(), expertAdvisorDto, model.kTP(), model.kTP());
  }

  @Override
  public PriceRiskLevelInput toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, PriceRiskLevelInputCreateDto createDto) {
    return new PriceRiskLevelInput(id, expertAdvisorDto.id(), createDto.kTP(), createDto.kTP());
  }
}

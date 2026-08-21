package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.PriceRiskLevelInputDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.PriceRiskLevelInputCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.PriceRiskLevelInputEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class PriceRiskLevelInputMapper implements InputMapper<PriceRiskLevelInputEntry, PriceRiskLevelInputDto, PriceRiskLevelInputCreateDto> {

  @Override
  public PriceRiskLevelInputDto toDto(PriceRiskLevelInputEntry priceRiskLevelInput) {
    return new PriceRiskLevelInputDto(priceRiskLevelInput.id(), priceRiskLevelInput.eaName(), priceRiskLevelInput.kTP(), priceRiskLevelInput.kSL());
  }

  @Override
  public PriceRiskLevelInputEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, PriceRiskLevelInputCreateDto priceRiskLevelInputCreateDto) {
    return new PriceRiskLevelInputEntry(id, expertAdvisorDto.eaName(), priceRiskLevelInputCreateDto.kTP(), priceRiskLevelInputCreateDto.kSL());
  }
}

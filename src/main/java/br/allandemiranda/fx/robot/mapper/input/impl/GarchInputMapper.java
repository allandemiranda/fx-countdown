package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.GarchInputDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.GarchInputCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.GarchInputEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class GarchInputMapper implements InputMapper<GarchInputEntry, GarchInputDto, GarchInputCreateDto> {

  @Override
  public GarchInputDto toDto(GarchInputEntry garchInput) {
    return new GarchInputDto(garchInput.id(), garchInput.eaName(), garchInput.horizon(), garchInput.priceSize());
  }

  @Override
  public GarchInputEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, GarchInputCreateDto garchInputCreateDto) {
    return new GarchInputEntry(id, expertAdvisorDto.eaName(), garchInputCreateDto.horizon(), garchInputCreateDto.priceSize());
  }
}

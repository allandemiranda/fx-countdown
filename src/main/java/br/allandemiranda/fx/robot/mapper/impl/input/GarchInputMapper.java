package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.GarchInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.GarchInputDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.GarchInput;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class GarchInputMapper implements InputMapper<GarchInput, GarchInputDto, GarchInputCreateDto> {

  @Override
  public GarchInputDto toDto(ExpertAdvisorDto expertAdvisorDto, GarchInput garchInput) {
    return new GarchInputDto(garchInput.id(), expertAdvisorDto, garchInput.horizon(), garchInput.priceSize());
  }

  @Override
  public GarchInput toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, GarchInputCreateDto garchInputCreateDto) {
    return new GarchInput(id, expertAdvisorDto.id(), garchInputCreateDto.horizon(), garchInputCreateDto.priceSize());
  }
}

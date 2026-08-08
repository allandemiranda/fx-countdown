package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.IBandsCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IBandsDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.IBands;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IBandsMapper implements InputMapper<IBands, IBandsDto, IBandsCreateDto> {

  public IBandsDto toDto(ExpertAdvisorDto expertAdvisorDto, IBands iBands) {
    return new IBandsDto(iBands.id(), expertAdvisorDto, iBands.period(), iBands.shift(), iBands.deviations(), iBands.applyTo());
  }

  public IBands toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IBandsCreateDto iBandsCreateDto) {
    return new IBands(id, expertAdvisorDto.id(), iBandsCreateDto.period(), iBandsCreateDto.shift(), iBandsCreateDto.deviations(), iBandsCreateDto.applyTo());
  }
}

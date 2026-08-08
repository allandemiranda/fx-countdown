package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMASlowCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMASlowDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.IMASlow;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IMASlowMapper implements InputMapper<IMASlow, IMASlowDto, IMASlowCreateDto> {

  @Override
  public IMASlowDto toDto(ExpertAdvisorDto expertAdvisorDto, IMASlow iMASlow) {
    return new IMASlowDto(iMASlow.id(), expertAdvisorDto, iMASlow.period(), iMASlow.shift(), iMASlow.method(), iMASlow.applyTo());
  }

  @Override
  public IMASlow toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IMASlowCreateDto iMASlowCreateDto) {
    return new IMASlow(id, expertAdvisorDto.id(), iMASlowCreateDto.period(), iMASlowCreateDto.shift(), iMASlowCreateDto.method(), iMASlowCreateDto.applyTo());
  }
}

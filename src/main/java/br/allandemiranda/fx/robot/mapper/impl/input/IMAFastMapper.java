package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMAFastCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMAFastDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.IMAFast;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IMAFastMapper implements InputMapper<IMAFast, IMAFastDto, IMAFastCreateDto> {

  @Override
  public IMAFastDto toDto(ExpertAdvisorDto expertAdvisorDto, IMAFast iMAFast) {
    return new IMAFastDto(iMAFast.id(), expertAdvisorDto, iMAFast.period(), iMAFast.shift(), iMAFast.method(), iMAFast.applyTo());
  }

  @Override
  public IMAFast toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IMAFastCreateDto iMAFastCreateDto) {
    return new IMAFast(id, expertAdvisorDto.id(), iMAFastCreateDto.period(), iMAFastCreateDto.shift(), iMAFastCreateDto.method(), iMAFastCreateDto.applyTo());
  }
}

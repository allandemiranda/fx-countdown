package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.ScopeInput;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ScopeInputMapper implements InputMapper<ScopeInput, ScopeInputDto, ScopeInputCreateDto> {

  @Override
  public ScopeInputDto toDto(ExpertAdvisorDto expertAdvisorDto, ScopeInput model) {
    return new ScopeInputDto(model.id(), expertAdvisorDto, model.startScope(), model.endScope());
  }

  @Override
  public ScopeInput toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, ScopeInputCreateDto createDto) {
    return new ScopeInput(id, expertAdvisorDto.id(), createDto.startScope(), createDto.endScope());
  }
}

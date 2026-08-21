package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.ScopeInputDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.ScopeInputEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ScopeInputMapper implements InputMapper<ScopeInputEntry, ScopeInputDto, ScopeInputCreateDto> {

  @Override
  public ScopeInputDto toDto(ScopeInputEntry scopeInput) {
    return new ScopeInputDto(scopeInput.id(), scopeInput.eaName(), scopeInput.startScope(), scopeInput.endScope());
  }

  @Override
  public ScopeInputEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, ScopeInputCreateDto scopeInputCreateDto) {
    return new ScopeInputEntry(id, expertAdvisorDto.eaName(), scopeInputCreateDto.startScope(), scopeInputCreateDto.endScope());
  }
}

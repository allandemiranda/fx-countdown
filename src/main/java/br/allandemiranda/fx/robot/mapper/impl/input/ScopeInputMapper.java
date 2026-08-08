package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.ScopeInput;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ScopeInputMapper implements InputDashboardMapper<ScopeInput, ScopeInputDto, ScopeInputCreateDto> {

  @Override
  public ScopeInputDto toDto(DashboardDto dashboardDto, ScopeInput model) {
    return new ScopeInputDto(model.id(), dashboardDto, model.startScope(), model.endScope());
  }

  @Override
  public ScopeInput toModel(UUID id, DashboardDto dashboardDto, ScopeInputCreateDto createDto) {
    return new ScopeInput(id, dashboardDto.id(), createDto.startScope(), createDto.endScope());
  }
}

package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.RiskLevelInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.RiskLevelInputDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.RiskLevelInput;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class RiskLevelInputMapper implements InputDashboardMapper<RiskLevelInput, RiskLevelInputDto, RiskLevelInputCreateDto> {

  @Override
  public RiskLevelInputDto toDto(DashboardDto dashboardDto, RiskLevelInput model) {
    return new RiskLevelInputDto(model.id(), dashboardDto, model.kTP(), model.kTP());
  }

  @Override
  public RiskLevelInput toModel(UUID id, DashboardDto dashboardDto, RiskLevelInputCreateDto createDto) {
    return new RiskLevelInput(id, dashboardDto.id(), createDto.kTP(), createDto.kTP());
  }
}

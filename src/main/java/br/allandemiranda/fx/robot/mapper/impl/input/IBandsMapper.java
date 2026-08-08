package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.IBandsCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IBandsDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.IBands;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IBandsMapper implements InputDashboardMapper<IBands, IBandsDto, IBandsCreateDto> {

  public IBandsDto toDto(DashboardDto dashboardDto, IBands iBands) {
    return new IBandsDto(iBands.id(), dashboardDto, iBands.period(), iBands.shift(), iBands.deviations(), iBands.applyTo());
  }

  public IBands toModel(UUID id, DashboardDto dashboardDto, IBandsCreateDto iBandsCreateDto) {
    return new IBands(id, dashboardDto.id(), iBandsCreateDto.period(), iBandsCreateDto.shift(), iBandsCreateDto.deviations(), iBandsCreateDto.applyTo());
  }
}

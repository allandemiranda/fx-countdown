package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMASlowCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMASlowDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.IMASlow;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IMASlowMapper implements InputDashboardMapper<IMASlow, IMASlowDto, IMASlowCreateDto> {

  @Override
  public IMASlowDto toDto(DashboardDto dashboardDto, IMASlow iMASlow) {
    return new IMASlowDto(iMASlow.id(), dashboardDto, iMASlow.period(), iMASlow.shift(), iMASlow.method(), iMASlow.applyTo());
  }

  @Override
  public IMASlow toModel(UUID id, DashboardDto dashboardDto, IMASlowCreateDto iMASlowCreateDto) {
    return new IMASlow(id, dashboardDto.id(), iMASlowCreateDto.period(), iMASlowCreateDto.shift(), iMASlowCreateDto.method(), iMASlowCreateDto.applyTo());
  }
}

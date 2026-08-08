package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMAFastCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMAFastDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.IMAFast;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IMAFastMapper implements InputDashboardMapper<IMAFast, IMAFastDto, IMAFastCreateDto> {

  @Override
  public IMAFastDto toDto(DashboardDto dashboardDto, IMAFast iMAFast) {
    return new IMAFastDto(iMAFast.id(), dashboardDto, iMAFast.period(), iMAFast.shift(), iMAFast.method(), iMAFast.applyTo());
  }

  @Override
  public IMAFast toModel(UUID id, DashboardDto dashboardDto, IMAFastCreateDto iMAFastCreateDto) {
    return new IMAFast(id, dashboardDto.id(), iMAFastCreateDto.period(), iMAFastCreateDto.shift(), iMAFastCreateDto.method(), iMAFastCreateDto.applyTo());
  }
}

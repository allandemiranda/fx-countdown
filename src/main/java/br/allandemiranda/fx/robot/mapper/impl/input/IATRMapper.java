package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.IATRCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IATRDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.IATR;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IATRMapper implements InputDashboardMapper<IATR, IATRDto, IATRCreateDto> {

  @Override
  public IATRDto toDto(DashboardDto dashboardDto, IATR iATR) {
    return new IATRDto(iATR.id(), dashboardDto, iATR.period());
  }

  @Override
  public IATR toModel(UUID id, DashboardDto dashboardDto, IATRCreateDto iATRCreateDto) {
    return new IATR(id, dashboardDto.id(), iATRCreateDto.period());
  }
}

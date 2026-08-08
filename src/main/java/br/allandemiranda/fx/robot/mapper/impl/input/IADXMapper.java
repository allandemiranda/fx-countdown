package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.IADXCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IADXDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.IADX;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IADXMapper implements InputDashboardMapper<IADX, IADXDto, IADXCreateDto> {

  public IADXDto toDto(DashboardDto dashboardDto, IADX iADX) {
    return new IADXDto(iADX.id(), dashboardDto, iADX.period());
  }

  public IADX toModel(UUID id, DashboardDto dashboardDto, IADXCreateDto iADXCreateDto) {
    return new IADX(id, dashboardDto.id(), iADXCreateDto.period());
  }
}

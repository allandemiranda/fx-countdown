package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.IRSICreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IRSIDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.IRSI;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IRSIMapper implements InputDashboardMapper<IRSI, IRSIDto, IRSICreateDto> {

  public IRSIDto toDto(DashboardDto dashboardDto, IRSI iRSI) {
    return new IRSIDto(iRSI.id(), dashboardDto, iRSI.period(), iRSI.applyTo());
  }

  public IRSI toModel(UUID id, DashboardDto dashboardDto, IRSICreateDto iRSICreateDto) {
    return new IRSI(id, dashboardDto.id(), iRSICreateDto.period(), iRSICreateDto.applyTo());
  }
}

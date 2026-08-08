package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMACDCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMACDDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.IMACD;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IMACDMapper implements InputDashboardMapper<IMACD, IMACDDto, IMACDCreateDto> {

  public IMACDDto toDto(DashboardDto dashboardDto, IMACD iMACD) {
    return new IMACDDto(iMACD.id(), dashboardDto, iMACD.fastEma(), iMACD.slowEma(), iMACD.macdSma(), iMACD.applyTo());
  }

  public IMACD toModel(UUID id, DashboardDto dashboardDto, IMACDCreateDto iMACDCreateDto) {
    return new IMACD(id, dashboardDto.id(), iMACDCreateDto.fastEma(), iMACDCreateDto.slowEma(), iMACDCreateDto.macdSma(), iMACDCreateDto.applyTo());
  }
}

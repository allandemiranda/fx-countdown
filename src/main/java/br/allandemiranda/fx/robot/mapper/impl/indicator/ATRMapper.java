package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATRCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATRDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.ATR;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ATRMapper implements IndicatorMapper<ATR, ATRDto, ATRCreateDto> {

  @Override
  public ATRDto toDto(DashboardDto dashboardDto, ATR atr) {
    return new ATRDto(atr.id(), dashboardDto, atr.timestamp(), atr.atr());
  }

  @Override
  public ATR toModel(UUID id, DashboardDto dashboardDto, ATRCreateDto atrCreateDto) {
    return new ATR(id, dashboardDto.id(), atrCreateDto.timestamp(), atrCreateDto.atr());
  }

}

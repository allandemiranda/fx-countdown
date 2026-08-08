package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.MACD;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MACDMapper implements IndicatorMapper<MACD, MACDDto, MACDCreateDto> {

  @Override
  public MACDDto toDto(DashboardDto dashboardDto, MACD macd) {
    return new MACDDto(macd.id(), dashboardDto, macd.timestamp(), macd.mainLine(), macd.signalLine());
  }

  @Override
  public MACD toModel(UUID id, DashboardDto dashboardDto, MACDCreateDto macdCreateDto) {
    return new MACD(id, dashboardDto.id(), macdCreateDto.timestamp(), macdCreateDto.mainLine(), macdCreateDto.signalLine());
  }

}

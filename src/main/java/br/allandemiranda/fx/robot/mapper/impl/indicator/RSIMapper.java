package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSICreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSIDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.RSI;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class RSIMapper implements IndicatorMapper<RSI, RSIDto, RSICreateDto> {

  @Override
  public RSIDto toDto(DashboardDto dashboardDto, RSI rsi) {
    return new RSIDto(rsi.id(), dashboardDto, rsi.timestamp(), rsi.rsi());
  }

  @Override
  public RSI toModel(UUID id, DashboardDto dashboardDto, RSICreateDto rsiCreateDto) {
    return new RSI(id, dashboardDto.id(), rsiCreateDto.timestamp(), rsiCreateDto.rsi());
  }

}

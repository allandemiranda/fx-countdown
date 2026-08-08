package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.StochasticCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.StochasticDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.Stochastic;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class StochasticMapper implements IndicatorMapper<Stochastic, StochasticDto, StochasticCreateDto> {

  @Override
  public StochasticDto toDto(DashboardDto dashboardDto, Stochastic stochastic) {
    return new StochasticDto(stochastic.id(), dashboardDto, stochastic.timestamp(), stochastic.mainLine(), stochastic.signalLine());
  }

  @Override
  public Stochastic toModel(UUID id, DashboardDto dashboardDto, StochasticCreateDto stochasticCreateDto) {
    return new Stochastic(id, dashboardDto.id(), stochasticCreateDto.timestamp(), stochasticCreateDto.mainLine(), stochasticCreateDto.signalLine());
  }

}

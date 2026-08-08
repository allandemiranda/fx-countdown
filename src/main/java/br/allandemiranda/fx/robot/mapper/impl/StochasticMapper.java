package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.impl.Stochastic;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class StochasticMapper implements ChartObjectMapper<Stochastic, StochasticDto, StochasticCreateDto> {

  @Override
  public StochasticDto toDto(ChartDto chartDto, Stochastic stochastic) {
    return new StochasticDto(stochastic.id(), chartDto, stochastic.timestamp(), stochastic.mainLine(), stochastic.signalLine());
  }

  @Override
  public Stochastic toModel(UUID id, ChartDto chartDto, StochasticCreateDto stochasticCreateDto) {
    return new Stochastic(id, chartDto.id(), stochasticCreateDto.timestamp(), stochasticCreateDto.mainLine(), stochasticCreateDto.signalLine());
  }

}

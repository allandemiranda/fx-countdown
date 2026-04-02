package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.Stochastic;
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

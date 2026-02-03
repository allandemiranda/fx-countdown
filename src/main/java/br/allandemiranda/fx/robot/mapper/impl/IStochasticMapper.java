package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.IStochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.IStochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.impl.IStochastic;
import org.springframework.stereotype.Component;

@Component
public final class IStochasticMapper implements InputObjectMapper<IStochastic, IStochasticDto, IStochasticCreateDto> {

  public IStochasticDto toDto(ChartDto chartDto, IStochastic iStochastic) {
    return new IStochasticDto(chartDto, iStochastic.kPeriod(), iStochastic.dPeriod(), iStochastic.slowing(), iStochastic.method(), iStochastic.priceField());
  }

  public IStochastic toModel(ChartDto chartDto, IStochasticCreateDto iStochasticCreateDto) {
    return new IStochastic(chartDto.id(), iStochasticCreateDto.kPeriod(), iStochasticCreateDto.dPeriod(), iStochasticCreateDto.slowing(), iStochasticCreateDto.method(), iStochasticCreateDto.priceField());
  }
}

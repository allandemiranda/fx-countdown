package br.allandemiranda.fx.robot.service.indicator.impl;

import br.allandemiranda.fx.robot.dto.indicator.StochasticDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.StochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.impl.StochasticMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.StochasticEntry;
import br.allandemiranda.fx.robot.repository.indicator.impl.StochasticRepository;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class StochasticService implements IndicatorService<StochasticEntry, StochasticDto, StochasticCreateDto> {

  private final StochasticMapper mapper;
  private final StochasticRepository repository;

}

package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.StochasticMapper;
import br.allandemiranda.fx.robot.model.impl.Stochastic;
import br.allandemiranda.fx.robot.repository.impl.StochasticRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class StochasticService implements ChartObjectService<Stochastic, StochasticDto, StochasticCreateDto> {

  private final StochasticRepository repository;

  private final StochasticMapper mapper;

}

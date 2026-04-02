package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.dto.base.StochasticDto;
import br.allandemiranda.fx.robot.mapper.StochasticMapper;
import br.allandemiranda.fx.robot.model.Stochastic;
import br.allandemiranda.fx.robot.repository.StochasticRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class StochasticService implements ChartObjectService<Stochastic, StochasticDto, StochasticCreateDto, StochasticRepository> {

  private final StochasticRepository repository;

  private final StochasticMapper mapper;

}

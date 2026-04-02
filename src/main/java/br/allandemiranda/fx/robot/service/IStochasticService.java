package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IStochasticDto;
import br.allandemiranda.fx.robot.dto.create.IStochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.IStochasticMapper;
import br.allandemiranda.fx.robot.model.IStochastic;
import br.allandemiranda.fx.robot.repository.IStochasticRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public final class IStochasticService implements InputObjectService<IStochastic, IStochasticDto, IStochasticCreateDto, IStochasticRepository> {

  private final IStochasticRepository repository;

  private final IStochasticMapper mapper;

}

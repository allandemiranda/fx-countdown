package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IStochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.IStochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IStochasticMapper;
import br.allandemiranda.fx.robot.model.impl.IStochastic;
import br.allandemiranda.fx.robot.repository.impl.IStochasticRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IStochasticService implements InputObjectService<IStochastic, IStochasticDto, IStochasticCreateDto> {

  private final IStochasticRepository repository;

  private final IStochasticMapper mapper;

}

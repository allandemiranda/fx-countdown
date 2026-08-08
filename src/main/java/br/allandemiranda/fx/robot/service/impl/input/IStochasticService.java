package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.IStochasticCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IStochasticDto;
import br.allandemiranda.fx.robot.mapper.impl.input.IStochasticMapper;
import br.allandemiranda.fx.robot.model.impl.input.IStochastic;
import br.allandemiranda.fx.robot.repository.impl.input.IStochasticRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IStochasticService implements InputDashboardService<IStochastic, IStochasticDto, IStochasticCreateDto> {

  private final IStochasticRepository repository;
  private final IStochasticMapper mapper;

}

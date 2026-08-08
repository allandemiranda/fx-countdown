package br.allandemiranda.fx.robot.service.impl.indicator;

import br.allandemiranda.fx.robot.dto.impl.indicator.StochasticCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.StochasticDto;
import br.allandemiranda.fx.robot.mapper.impl.indicator.StochasticMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.Stochastic;
import br.allandemiranda.fx.robot.repository.impl.indicator.StochasticRepository;
import br.allandemiranda.fx.robot.service.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class StochasticService implements IndicatorService<Stochastic, StochasticDto, StochasticCreateDto> {

  private final StochasticRepository repository;

  private final StochasticMapper mapper;

}

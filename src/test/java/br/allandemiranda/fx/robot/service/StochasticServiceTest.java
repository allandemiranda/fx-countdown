package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.StochasticMapper;
import br.allandemiranda.fx.robot.model.Stochastic;
import br.allandemiranda.fx.robot.repository.StochasticRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StochasticServiceTest extends AbstractChartObjectServiceTest<Stochastic, StochasticDto, StochasticCreateDto> {

  @Mock
  @Getter
  private Stochastic model;

  @Mock
  @Getter
  private StochasticCreateDto createDto;

  @Mock
  @Getter
  private StochasticRepository repository;

  @Spy
  @Getter
  private StochasticMapper mapper;

  @InjectMocks
  @Getter
  private StochasticService service;

}
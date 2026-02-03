package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.StochasticMapper;
import br.allandemiranda.fx.robot.model.impl.Stochastic;
import br.allandemiranda.fx.robot.repository.impl.StochasticRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StochasticServiceTest extends ChartObjectServiceTest<Stochastic, StochasticDto, StochasticCreateDto> {

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

  @Spy
  @InjectMocks
  @Getter
  private StochasticService service;

}
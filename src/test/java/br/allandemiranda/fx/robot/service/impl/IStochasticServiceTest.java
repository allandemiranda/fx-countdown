package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IStochasticDto;
import br.allandemiranda.fx.robot.dto.impl.create.IStochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IStochasticMapper;
import br.allandemiranda.fx.robot.model.impl.IStochastic;
import br.allandemiranda.fx.robot.repository.impl.IStochasticRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IStochasticServiceTest extends InputObjectServiceTest<IStochastic, IStochasticDto, IStochasticCreateDto> {

  @Mock
  @Getter
  private IStochastic model;

  @Mock
  @Getter
  private IStochasticRepository repository;

  @Spy
  @Getter
  private IStochasticMapper mapper;

  @InjectMocks
  @Getter
  private IStochasticService service;

  @Mock
  @Getter
  private IStochasticCreateDto createDto;
}

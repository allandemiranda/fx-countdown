package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IStochasticDto;
import br.allandemiranda.fx.robot.dto.create.IStochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.IStochasticMapper;
import br.allandemiranda.fx.robot.model.IStochastic;
import br.allandemiranda.fx.robot.repository.IStochasticRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractInputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IStochasticServiceTest extends AbstractInputObjectServiceTest<IStochastic, IStochasticDto, IStochasticCreateDto> {

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

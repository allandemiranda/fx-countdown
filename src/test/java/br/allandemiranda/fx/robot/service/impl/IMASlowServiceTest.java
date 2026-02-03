package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IMASlowDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMASlowCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IMASlowMapper;
import br.allandemiranda.fx.robot.model.impl.IMASlow;
import br.allandemiranda.fx.robot.repository.impl.IMASlowRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IMASlowServiceTest extends InputObjectServiceTest<IMASlow, IMASlowDto, IMASlowCreateDto> {

  @Mock
  @Getter
  private IMASlow model;

  @Mock
  @Getter
  private IMASlowRepository repository;

  @Spy
  @Getter
  private IMASlowMapper mapper;

  @InjectMocks
  @Getter
  private IMASlowService service;

  @Mock
  @Getter
  private IMASlowCreateDto createDto;
}

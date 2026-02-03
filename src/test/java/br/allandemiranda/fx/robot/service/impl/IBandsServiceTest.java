package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.impl.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IBandsMapper;
import br.allandemiranda.fx.robot.model.impl.IBands;
import br.allandemiranda.fx.robot.repository.impl.IBandsRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IBandsServiceTest extends InputObjectServiceTest<IBands, IBandsDto, IBandsCreateDto> {

  @Mock
  @Getter
  private IBands model;

  @Mock
  @Getter
  private IBandsRepository repository;

  @Spy
  @Getter
  private IBandsMapper mapper;

  @InjectMocks
  @Getter
  private IBandsService service;

  @Mock
  @Getter
  private IBandsCreateDto createDto;
}

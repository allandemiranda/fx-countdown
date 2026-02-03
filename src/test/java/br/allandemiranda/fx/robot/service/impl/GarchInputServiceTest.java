package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.GarchInputDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.GarchInputMapper;
import br.allandemiranda.fx.robot.model.impl.GarchInput;
import br.allandemiranda.fx.robot.repository.impl.GarchInputRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GarchInputServiceTest extends InputObjectServiceTest<GarchInput, GarchInputDto, GarchInputCreateDto> {

  @Mock
  @Getter
  private GarchInput model;

  @Mock
  @Getter
  private GarchInputRepository repository;

  @Spy
  @Getter
  private GarchInputMapper mapper;

  @InjectMocks
  @Getter
  private GarchInputService service;

  @Mock
  @Getter
  private GarchInputCreateDto createDto;
}

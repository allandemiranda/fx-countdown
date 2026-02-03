package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IMAFastDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMAFastCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IMAFastMapper;
import br.allandemiranda.fx.robot.model.impl.IMAFast;
import br.allandemiranda.fx.robot.repository.impl.IMAFastRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IMAFastServiceTest extends InputObjectServiceTest<IMAFast, IMAFastDto, IMAFastCreateDto> {

  @Mock
  @Getter
  private IMAFast model;

  @Mock
  @Getter
  private IMAFastRepository repository;

  @Spy
  @Getter
  private IMAFastMapper mapper;

  @InjectMocks
  @Getter
  private IMAFastService service;

  @Mock
  @Getter
  private IMAFastCreateDto createDto;
}

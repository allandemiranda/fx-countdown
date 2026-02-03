package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.impl.create.IRSICreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IRSIMapper;
import br.allandemiranda.fx.robot.model.impl.IRSI;
import br.allandemiranda.fx.robot.repository.impl.IRSIRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IRSIServiceTest extends InputObjectServiceTest<IRSI, IRSIDto, IRSICreateDto> {

  @Mock
  @Getter
  private IRSI model;

  @Mock
  @Getter
  private IRSIRepository repository;

  @Spy
  @Getter
  private IRSIMapper mapper;

  @InjectMocks
  @Getter
  private IRSIService service;

  @Mock
  @Getter
  private IRSICreateDto createDto;
}

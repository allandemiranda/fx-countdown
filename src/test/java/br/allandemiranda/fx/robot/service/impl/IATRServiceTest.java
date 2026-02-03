package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IATRDto;
import br.allandemiranda.fx.robot.dto.impl.create.IATRCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IATRMapper;
import br.allandemiranda.fx.robot.model.impl.IATR;
import br.allandemiranda.fx.robot.repository.impl.IATRRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IATRServiceTest extends InputObjectServiceTest<IATR, IATRDto, IATRCreateDto> {

  @Mock
  @Getter
  private IATR model;

  @Mock
  @Getter
  private IATRRepository repository;

  @Spy
  @Getter
  private IATRMapper mapper;

  @InjectMocks
  @Getter
  private IATRService service;

  @Mock
  @Getter
  private IATRCreateDto createDto;
}

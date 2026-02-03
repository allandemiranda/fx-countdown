package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IADXDto;
import br.allandemiranda.fx.robot.dto.impl.create.IADXCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IADXMapper;
import br.allandemiranda.fx.robot.model.impl.IADX;
import br.allandemiranda.fx.robot.repository.impl.IADXRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IADXServiceTest extends InputObjectServiceTest<IADX, IADXDto, IADXCreateDto> {

  @Mock
  @Getter
  private IADX model;

  @Mock
  @Getter
  private IADXRepository repository;

  @Spy
  @Getter
  private IADXMapper mapper;

  @InjectMocks
  @Getter
  private IADXService service;

  @Mock
  @Getter
  private IADXCreateDto createDto;
}

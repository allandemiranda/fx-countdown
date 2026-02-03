package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IMACDDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMACDCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IMACDMapper;
import br.allandemiranda.fx.robot.model.impl.IMACD;
import br.allandemiranda.fx.robot.repository.impl.IMACDRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IMACDServiceTest extends InputObjectServiceTest<IMACD, IMACDDto, IMACDCreateDto> {

  @Mock
  @Getter
  private IMACD model;

  @Mock
  @Getter
  private IMACDRepository repository;

  @Spy
  @Getter
  private IMACDMapper mapper;

  @InjectMocks
  @Getter
  private IMACDService service;

  @Mock
  @Getter
  private IMACDCreateDto createDto;
}

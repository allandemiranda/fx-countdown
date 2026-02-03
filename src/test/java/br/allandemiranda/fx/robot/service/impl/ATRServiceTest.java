package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ATRDto;
import br.allandemiranda.fx.robot.dto.impl.create.ATRCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.ATRMapper;
import br.allandemiranda.fx.robot.model.impl.ATR;
import br.allandemiranda.fx.robot.repository.impl.ATRRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ATRServiceTest extends ChartObjectServiceTest<ATR, ATRDto, ATRCreateDto> {

  @Mock
  @Getter
  private ATR model;

  @Mock
  @Getter
  private ATRCreateDto createDto;

  @Mock
  @Getter
  private ATRRepository repository;

  @Spy
  @Getter
  private ATRMapper mapper;

  @Spy
  @InjectMocks
  @Getter
  private ATRService service;

}
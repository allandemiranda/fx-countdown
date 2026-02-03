package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.RSIDto;
import br.allandemiranda.fx.robot.dto.impl.create.RSICreateDto;
import br.allandemiranda.fx.robot.mapper.impl.RSIMapper;
import br.allandemiranda.fx.robot.model.impl.RSI;
import br.allandemiranda.fx.robot.repository.impl.RSIRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RSIServiceTest extends ChartObjectServiceTest<RSI, RSIDto, RSICreateDto> {

  @Mock
  @Getter
  private RSI model;

  @Mock
  @Getter
  private RSICreateDto createDto;

  @Mock
  @Getter
  private RSIRepository repository;

  @Spy
  @Getter
  private RSIMapper mapper;

  @Spy
  @InjectMocks
  @Getter
  private RSIService service;

}
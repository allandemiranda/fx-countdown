package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.MACDDto;
import br.allandemiranda.fx.robot.dto.impl.create.MACDCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.MACDMapper;
import br.allandemiranda.fx.robot.model.impl.MACD;
import br.allandemiranda.fx.robot.repository.impl.MACDRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MACDServiceTest extends ChartObjectServiceTest<MACD, MACDDto, MACDCreateDto> {

  @Mock
  @Getter
  private MACD model;

  @Mock
  @Getter
  private MACDCreateDto createDto;

  @Mock
  @Getter
  private MACDRepository repository;

  @Spy
  @Getter
  private MACDMapper mapper;

  @Spy
  @InjectMocks
  @Getter
  private MACDService service;

}
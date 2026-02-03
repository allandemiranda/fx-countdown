package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.GarchTradingMapper;
import br.allandemiranda.fx.robot.model.impl.GarchTrading;
import br.allandemiranda.fx.robot.repository.impl.GarchTradingRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarchTradingServiceTest extends ChartObjectServiceTest<GarchTrading, GarchTradingDto, GarchTradingCreateDto> {

  @Mock
  @Getter
  private GarchTrading model;

  @Mock
  @Getter
  private GarchTradingCreateDto createDto;

  @Mock
  @Getter
  private GarchTradingRepository repository;

  @Spy
  @Getter
  private GarchTradingMapper mapper;

  @Spy
  @InjectMocks
  @Getter
  private GarchTradingService service;

}
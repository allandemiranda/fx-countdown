package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.dto.CandlestickCreateDto;
import br.allandemiranda.fx.robot.mapper.CandlestickMapper;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.repository.impl.CandlestickRepository;
import br.allandemiranda.fx.robot.service.CandlestickService;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CandlestickServiceTest extends ChartObjectServiceTest<Candlestick, CandlestickDto, CandlestickCreateDto> {

  @Mock
  @Getter
  private Candlestick model;

  @Mock
  @Getter
  private CandlestickCreateDto createDto;

  @Mock
  @Getter
  private CandlestickRepository repository;

  @Spy
  @Getter
  private CandlestickMapper mapper;

  @Spy
  @InjectMocks
  @Getter
  private CandlestickService service;

}
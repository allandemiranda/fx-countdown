package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.CandlestickDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.create.CandlestickCreateDto;
import br.allandemiranda.fx.robot.mapper.CandlestickMapper;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.repository.CandlestickRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CandlestickServiceTest extends AbstractChartObjectServiceTest<Candlestick, CandlestickDto, CandlestickCreateDto> {

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

  @InjectMocks
  @Getter
  private CandlestickService service;

}
package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.MACDDto;
import br.allandemiranda.fx.robot.dto.create.MACDCreateDto;
import br.allandemiranda.fx.robot.mapper.MACDMapper;
import br.allandemiranda.fx.robot.model.MACD;
import br.allandemiranda.fx.robot.repository.MACDRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MACDServiceTest extends AbstractChartObjectServiceTest<MACD, MACDDto, MACDCreateDto> {

  @Mock
  @Getter
  private MACD model;

  @Mock
  @Getter
  private MACDRepository repository;

  @Spy
  @Getter
  private MACDMapper mapper;

  @InjectMocks
  @Getter
  private MACDService service;

}
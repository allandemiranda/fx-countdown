package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.mapper.GarchTradingMapper;
import br.allandemiranda.fx.robot.model.GarchTrading;
import br.allandemiranda.fx.robot.repository.GarchTradingRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarchTradingServiceTest extends AbstractChartObjectServiceTest<GarchTrading, GarchTradingDto, GarchTradingCreateDto> {

  @Mock
  @Getter
  private GarchTrading model;

  @Mock
  @Getter
  private GarchTradingRepository repository;

  @Spy
  @Getter
  private GarchTradingMapper mapper;

  @InjectMocks
  @Getter
  private GarchTradingService service;

}
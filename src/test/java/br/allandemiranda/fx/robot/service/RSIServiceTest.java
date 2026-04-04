package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.RSIDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.create.RSICreateDto;
import br.allandemiranda.fx.robot.mapper.RSIMapper;
import br.allandemiranda.fx.robot.model.RSI;
import br.allandemiranda.fx.robot.repository.RSIRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RSIServiceTest extends AbstractChartObjectServiceTest<RSI, RSIDto, RSICreateDto> {

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

  @InjectMocks
  @Getter
  private RSIService service;

}
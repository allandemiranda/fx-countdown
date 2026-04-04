package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.create.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.mapper.GarchForecastMapper;
import br.allandemiranda.fx.robot.model.GarchForecast;
import br.allandemiranda.fx.robot.repository.GarchForecastRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarchForecastServiceTest extends AbstractChartObjectServiceTest<GarchForecast, GarchForecastDto, GarchForecastCreateDto> {

  @Mock
  @Getter
  private GarchForecast model;

  @Mock
  @Getter
  private GarchForecastCreateDto createDto;

  @Mock
  @Getter
  private GarchForecastRepository repository;

  @Spy
  @Getter
  private GarchForecastMapper mapper;

  @InjectMocks
  @Getter
  private GarchForecastService service;

}
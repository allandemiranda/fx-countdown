package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.GarchForecastMapper;
import br.allandemiranda.fx.robot.model.impl.GarchForecast;
import br.allandemiranda.fx.robot.repository.impl.GarchForecastRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarchForecastServiceTest extends ChartObjectServiceTest<GarchForecast, GarchForecastDto, GarchForecastCreateDto> {

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

  @Spy
  @InjectMocks
  @Getter
  private GarchForecastService service;

}
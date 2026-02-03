package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.GarchForecastMapper;
import br.allandemiranda.fx.robot.model.impl.GarchForecast;
import br.allandemiranda.fx.robot.repository.impl.GarchForecastRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class GarchForecastService implements ChartObjectService<GarchForecast, GarchForecastDto, GarchForecastCreateDto> {

  private final GarchForecastRepository repository;

  private final GarchForecastMapper mapper;

}

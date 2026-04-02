package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.create.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.mapper.GarchForecastMapper;
import br.allandemiranda.fx.robot.model.GarchForecast;
import br.allandemiranda.fx.robot.repository.GarchForecastRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public final class GarchForecastService implements ChartObjectService<GarchForecast, GarchForecastDto, GarchForecastCreateDto, GarchForecastRepository> {

  private final GarchForecastRepository repository;

  private final GarchForecastMapper mapper;

}

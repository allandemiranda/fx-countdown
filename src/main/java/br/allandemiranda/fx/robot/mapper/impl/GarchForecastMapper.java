package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.mapper.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.impl.GarchForecast;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class GarchForecastMapper implements ChartObjectMapper<GarchForecast, GarchForecastDto, GarchForecastCreateDto> {

  @Override
  public GarchForecastDto toDto(ChartDto chartDto, GarchForecast garchForecast) {
    return new GarchForecastDto(garchForecast.id(), chartDto, garchForecast.timestamp(), garchForecast.omega(), garchForecast.alpha(), garchForecast.beta(), garchForecast.sigmaAgg());
  }

  @Override
  public GarchForecast toModel(UUID id, ChartDto chartDto, GarchForecastCreateDto garchForecastCreateDto) {
    return new GarchForecast(id, chartDto.id(), garchForecastCreateDto.timestamp(), garchForecastCreateDto.omega(), garchForecastCreateDto.alpha(), garchForecastCreateDto.beta(), garchForecastCreateDto.sigmaAgg());
  }

}

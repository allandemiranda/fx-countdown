package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.dto.base.GarchForecastDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.GarchForecast;
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

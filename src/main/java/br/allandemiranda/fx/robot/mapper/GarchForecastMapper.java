package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.dto.GarchForecastDto;
import br.allandemiranda.fx.robot.model.GarchForecast;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GarchForecastMapper {

  public static @NonNull GarchForecastDto toGarchForecastDto(@NonNull ChartDto chartDto, @NonNull GarchForecast garchForecast) {
    return new GarchForecastDto(garchForecast.id(), chartDto, garchForecast.timestamp(), garchForecast.omega(), garchForecast.alpha(), garchForecast.beta(), garchForecast.sigmaAgg());
  }

  public static @NonNull GarchForecast toGarchForecast(UUID id, @NonNull ChartDto chartDto, @NonNull GarchForecastCreateDto garchForecastCreateDto) {
    return new GarchForecast(id, chartDto.id(), garchForecastCreateDto.timestamp(), garchForecastCreateDto.omega(), garchForecastCreateDto.alpha(), garchForecastCreateDto.beta(), garchForecastCreateDto.sigmaAgg());
  }

}

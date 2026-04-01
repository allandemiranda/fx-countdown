package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.dto.GarchForecastDto;
import br.allandemiranda.fx.robot.mapper.GarchForecastMapper;
import br.allandemiranda.fx.robot.model.GarchForecast;
import br.allandemiranda.fx.robot.repository.GarchForecastRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class GarchForecastService {

  private final GarchForecastRepository repository;

  @Transactional(readOnly = true)
  public Mono<GarchForecastDto> getGarchForecast(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findGarchForecast(chartDto.id(), timestamp).map(garchForecast -> GarchForecastMapper.toGarchForecastDto(chartDto, garchForecast));
  }

  @Transactional(readOnly = true)
  public Flux<GarchForecastDto> getGarchForecast(@NonNull ChartDto chartDto) {
    return this.getRepository().findGarchForecasts(chartDto.id()).map(garchForecast -> GarchForecastMapper.toGarchForecastDto(chartDto, garchForecast));
  }

  public Mono<GarchForecastDto> createGarchForecast(ChartDto chartDto, GarchForecastCreateDto garchForecastCreateDto) {
    GarchForecast model = GarchForecastMapper.toGarchForecast(UUID.randomUUID(), chartDto, garchForecastCreateDto);
    return this.getRepository().save(model).map(garchForecast -> GarchForecastMapper.toGarchForecastDto(chartDto, garchForecast));
  }

  public Mono<Void> deleteGarchForecast(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteGarchForecast(chartDto.id());
  }

}

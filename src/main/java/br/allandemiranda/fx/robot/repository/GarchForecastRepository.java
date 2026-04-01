package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.GarchForecast;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GarchForecastRepository extends ReactiveCrudRepository<GarchForecast, UUID> {

  @Query("SELECT * FROM garch_forecast WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<GarchForecast> findGarchForecast(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM garch_forecast WHERE chart_id = :chartId")
  Flux<GarchForecast> findGarchForecasts(UUID chartId);

  @Query("DELETE FROM garch_forecast WHERE chart_id = :chartId")
  Mono<Void> deleteGarchForecast(UUID chartId);
}
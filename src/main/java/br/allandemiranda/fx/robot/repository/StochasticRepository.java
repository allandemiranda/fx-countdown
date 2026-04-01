package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Stochastic;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StochasticRepository extends ReactiveCrudRepository<Stochastic, UUID> {

  @Query("SELECT * FROM indicator_stochastic WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<Stochastic> findStochastic(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM indicator_stochastic WHERE chart_id = :chartId")
  Flux<Stochastic> findStochastics(UUID chartId);

  @Query("DELETE FROM indicator_stochastic WHERE chart_id = :chartId")
  Mono<Void> deleteStochastic(UUID chartId);

}
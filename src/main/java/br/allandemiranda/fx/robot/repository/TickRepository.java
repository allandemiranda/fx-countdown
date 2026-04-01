package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Tick;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TickRepository extends ReactiveCrudRepository<Tick, UUID> {

  @Query("SELECT * FROM tick WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<Tick> findTick(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM tick WHERE chart_id = :chartId")
  Flux<Tick> findTicks(UUID chartId);

  @Query("DELETE FROM tick WHERE chart_id = :chartId")
  Mono<Void> deleteChart(UUID chartId);

}
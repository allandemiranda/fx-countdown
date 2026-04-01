package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MaSlow;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MaSlowRepository extends ReactiveCrudRepository<MaSlow, UUID> {

  @Query("SELECT * FROM indicator_ma_slow WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<MaSlow> findMaSlow(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM indicator_ma_slow WHERE chart_id = :chartId")
  Flux<MaSlow> findMaSlows(UUID chartId);

  @Query("DELETE FROM indicator_ma_slow WHERE chart_id = :chartId")
  Mono<Void> deleteMaSlow(UUID chartId);
}
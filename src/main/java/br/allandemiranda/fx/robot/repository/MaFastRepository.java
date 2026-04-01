package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MaFast;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MaFastRepository extends ReactiveCrudRepository<MaFast, UUID> {

  @Query("SELECT * FROM indicator_ma_fast WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<MaFast> findMaFast(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM indicator_ma_fast WHERE chart_id = :chartId")
  Flux<MaFast> findMaFasts(UUID chartId);

  @Query("DELETE FROM indicator_ma_fast WHERE chart_id = :chartId")
  Mono<Void> deleteMaFast(UUID chartId);
}
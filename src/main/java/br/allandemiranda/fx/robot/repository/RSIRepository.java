package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.RSI;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RSIRepository extends ReactiveCrudRepository<RSI, UUID> {

  @Query("SELECT * FROM indicator_rsi WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<RSI> findRSI(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM indicator_rsi WHERE chart_id = :chartId")
  Flux<RSI> findRSIs(UUID chartId);

  @Query("DELETE FROM indicator_rsi WHERE chart_id = :chartId")
  Mono<Void> deleteRSI(UUID chartId);

}
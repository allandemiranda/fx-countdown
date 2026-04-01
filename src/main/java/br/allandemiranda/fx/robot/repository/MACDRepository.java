package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MACD;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MACDRepository extends ReactiveCrudRepository<MACD, UUID> {

  @Query("SELECT * FROM indicator_macd WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<MACD> findMACD(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM indicator_macd WHERE chart_id = :chartId")
  Flux<MACD> findMACDs(UUID chartId);

  @Query("DELETE FROM indicator_macd WHERE chart_id = :chartId")
  Mono<Void> deleteMACD(UUID chartId);

}
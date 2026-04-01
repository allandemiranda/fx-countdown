package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Candlestick;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CandlestickRepository extends ReactiveCrudRepository<Candlestick, UUID> {

  @Query("SELECT * FROM candlestick WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<Candlestick> findCandlestick(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM candlestick WHERE chart_id = :chartId")
  Flux<Candlestick> findCandlesticks(UUID chartId);

  @Query("DELETE FROM candlestick WHERE chart_id = :chartId")
  Mono<Void> deleteCandlestick(UUID chartId);
}
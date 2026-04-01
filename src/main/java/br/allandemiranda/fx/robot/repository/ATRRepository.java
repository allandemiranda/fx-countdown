package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.model.ATR;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ATRRepository extends ReactiveCrudRepository<ATR, UUID> {

  @Query("SELECT * FROM indicator_atr WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<ADX> findATR(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM indicator_atr WHERE chart_id = :chartId")
  Flux<ATR> findATRs(UUID chartId);

  @Query("DELETE FROM indicator_atr WHERE chart_id = :chartId")
  Mono<Void> deleteATR(UUID chartId);

}
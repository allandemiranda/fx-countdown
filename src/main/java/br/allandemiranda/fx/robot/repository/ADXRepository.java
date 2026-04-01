package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.ADX;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ADXRepository extends ReactiveCrudRepository<ADX, UUID> {

  @Query("SELECT * FROM indicator_adx WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<ADX> findADX(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM indicator_adx WHERE chart_id = :chartId")
  Flux<ADX> findADXs(UUID chartId);

  @Query("DELETE FROM indicator_adx WHERE chart_id = :chartId")
  Mono<Void> deleteADX(UUID chartId);

}
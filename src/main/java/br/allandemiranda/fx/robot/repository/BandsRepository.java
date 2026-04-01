package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Bands;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BandsRepository extends ReactiveCrudRepository<Bands, UUID> {

  @Query("SELECT * FROM indicator_bands WHERE chart_id = :chartId AND timestamp = :timestamp LIMIT 1")
  Mono<Bands> findBands(UUID chartId, OffsetDateTime timestamp);

  @Query("SELECT * FROM indicator_bands WHERE chart_id = :chartId")
  Flux<Bands> findBandss(UUID chartId);

  @Query("DELETE FROM indicator_bands WHERE chart_id = :chartId")
  Mono<Void> deleteBands(UUID chartId);

}
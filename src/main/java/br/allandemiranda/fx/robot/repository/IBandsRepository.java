package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IBands;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IBandsRepository extends ReactiveCrudRepository<IBands, UUID> {

  @Query("SELECT * FROM i_bands WHERE chart_id = :chartId LIMIT 1")
  Mono<IBands> findIBands(UUID chartId);

  @Query("DELETE FROM i_bands WHERE chart_id = :chartId")
  Mono<Void> deleteIBands(UUID chartId);

}
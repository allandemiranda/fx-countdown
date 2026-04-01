package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.GarchInput;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface GarchInputRepository extends ReactiveCrudRepository<GarchInput, UUID> {

  @Query("SELECT * FROM garch_input WHERE chart_id = :chartId LIMIT 1")
  Mono<GarchInput> findGarchInput(UUID chartId);

  @Query("DELETE FROM garch_input WHERE chart_id = :chartId")
  Mono<Void> deleteGarchInput(UUID chartId);

}
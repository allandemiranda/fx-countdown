package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MLInput;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface MLInputRepository extends ReactiveCrudRepository<MLInput, UUID> {

  @Query("SELECT * FROM ml_input WHERE chart_id = :chartId LIMIT 1")
  Mono<MLInput> findMLInput(UUID chartId);

  @Query("DELETE FROM ml_input WHERE chart_id = :chartId")
  Mono<Void> deleteMLInput(UUID chartId);

}
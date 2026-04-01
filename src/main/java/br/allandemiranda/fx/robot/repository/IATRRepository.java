package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IATR;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IATRRepository extends ReactiveCrudRepository<IATR, UUID> {

  @Query("SELECT * FROM i_atr WHERE chart_id = :chartId LIMIT 1")
  Mono<IATR> findIATR(UUID chartId);

  @Query("DELETE FROM i_atr WHERE chart_id = :chartId")
  Mono<Void> deleteIATR(UUID chartId);

}
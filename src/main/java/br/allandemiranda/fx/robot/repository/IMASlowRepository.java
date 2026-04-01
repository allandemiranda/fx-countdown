package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IMASlow;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IMASlowRepository extends ReactiveCrudRepository<IMASlow, UUID> {

  @Query("SELECT * FROM i_ma_slow WHERE chart_id = :chartId LIMIT 1")
  Mono<IMASlow> findIMASlow(UUID chartId);

  @Query("DELETE FROM i_ma_slow WHERE chart_id = :chartId")
  Mono<Void> deleteIMASlow(UUID chartId);
}
package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IStochastic;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IStochasticRepository extends ReactiveCrudRepository<IStochastic, UUID> {

  @Query("SELECT * FROM i_stochastic WHERE chart_id = :chartId LIMIT 1")
  Mono<IStochastic> findIStochastic(UUID chartId);

  @Query("DELETE FROM i_stochastic WHERE chart_id = :chartId")
  Mono<Void> deleteIStochastic(UUID chartId);

}
package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IMAFast;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IMAFastRepository extends ReactiveCrudRepository<IMAFast, UUID> {

  @Query("SELECT * FROM i_ma_fast WHERE chart_id = :chartId LIMIT 1")
  Mono<IMAFast> findIMAFast(UUID chartId);

  @Query("DELETE FROM i_ma_fast WHERE chart_id = :chartId")
  Mono<Void> deleteIMAFast(UUID chartId);

}
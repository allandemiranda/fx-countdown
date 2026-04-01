package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IRSI;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IRSIRepository extends ReactiveCrudRepository<IRSI, UUID> {

  @Query("SELECT * FROM i_rsi WHERE chart_id = :chartId LIMIT 1")
  Mono<IRSI> findIRSI(UUID chartId);

  @Query("DELETE FROM i_rsi WHERE chart_id = :chartId")
  Mono<Void> deleteIRSI(UUID chartId);

}
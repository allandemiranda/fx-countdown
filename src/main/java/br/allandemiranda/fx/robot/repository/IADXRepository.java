package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IADX;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IADXRepository extends ReactiveCrudRepository<IADX, UUID> {

  @Query("SELECT * FROM i_adx WHERE chart_id = :chartId LIMIT 1")
  Mono<IADX> findIADX(UUID chartId);

  @Query("DELETE FROM i_adx WHERE chart_id = :chartId")
  Mono<Void> deleteIADX(UUID chartId);

}
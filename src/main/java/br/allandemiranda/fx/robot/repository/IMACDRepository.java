package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IMACD;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IMACDRepository extends ReactiveCrudRepository<IMACD, UUID> {

  @Query("SELECT * FROM i_macd WHERE chart_id = :chartId LIMIT 1")
  Mono<IMACD> findIMACD(UUID chartId);

  @Query("DELETE FROM i_macd WHERE chart_id = :chartId")
  Mono<Void> deleteIMACD(UUID chartId);

}
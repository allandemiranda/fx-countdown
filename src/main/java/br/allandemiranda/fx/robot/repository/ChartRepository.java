package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.Chart;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChartRepository extends ReactiveCrudRepository<Chart, UUID> {

  @Query("SELECT * FROM chart WHERE symbol_name = :symbolName AND period = :period LIMIT 1")
  Mono<Chart> findChart(String symbolName, Timeframe period);

  @Query("SELECT * FROM chart WHERE symbol_name = :symbolName")
  Flux<Chart> findCharts(String symbolName);

  @Query("DELETE FROM chart WHERE symbol_name = :symbolName AND period = :period")
  Mono<Void> deleteChart(String symbolName, Timeframe period);

}
package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.Chart;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ChartRepository extends ReactiveCrudRepository<Chart, UUID> {

  Mono<Chart> findBySymbolNameAndPeriod(String symbolName, Timeframe period);

  Flux<Chart> findBySymbolName(String symbolName);

  Mono<Void> deleteAllBySymbolNameAndPeriod(String symbolName, Timeframe period);

}
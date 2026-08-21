package br.allandemiranda.fx.robot.repository.analysis;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.analysis.impl.XgBoostEntry;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface XgBoostRepository extends ReactiveCrudRepository<XgBoostEntry, UUID> {

  Mono<XgBoostEntry> findByEaNameAndVersion(String eaName, String version);

  Flux<XgBoostEntry> findBySymbolNameAndTimeframeAndEaName(String symbolName, Timeframe timeframe, String eaName);

  Mono<XgBoostEntry> findBySymbolNameAndTimeframeAndEaNameAndVersion(String symbolName, Timeframe timeframe, String eaName, String version);

}

package br.allandemiranda.fx.robot.repository.ea;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.ea.impl.ExpertAdvisorEntry;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ExpertAdvisorRepository extends ReactiveCrudRepository<ExpertAdvisorEntry, String> {

  Flux<ExpertAdvisorEntry> findBySymbolNameAndTimeframe(String symbolName, Timeframe timeframe);

  Mono<ExpertAdvisorEntry> findFirstByEaNameAndSymbolNameAndTimeframe(String eaName, String symbolName, Timeframe timeframe);

}

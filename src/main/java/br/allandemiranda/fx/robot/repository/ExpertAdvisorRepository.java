package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.ExpertAdvisor;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ExpertAdvisorRepository extends ReactiveCrudRepository<ExpertAdvisor, UUID> {

  Mono<ExpertAdvisor> findFirstByNameAndSymbolNameAndTimeframe(String name, String symbolName, Timeframe timeframe);

  Flux<ExpertAdvisor> findBySymbolNameAndTimeframe(String symbolName, Timeframe timeframe);

}
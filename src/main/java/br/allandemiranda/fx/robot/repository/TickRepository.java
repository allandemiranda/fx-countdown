package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Tick;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TickRepository extends ReactiveCrudRepository<Tick, UUID> {

  Mono<Tick> findBySymbolNameAndTimestamp(String symbolName, OffsetDateTime timestamp);

  Flux<Tick> findAllBySymbolName(String symbolName);

  Mono<Void> deleteAllBySymbolName(String symbolName);

}
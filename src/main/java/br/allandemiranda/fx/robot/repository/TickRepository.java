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

  Flux<Tick> findAllBySymbolNameOrderByTimestampAsc(String symbolName);

  Mono<Tick> findFirstBySymbolNameOrderByTimestampAsc(String symbolName);

  Mono<Tick> findFirstBySymbolNameOrderByTimestampDesc(String symbolName);

  Flux<Tick> findAllBySymbolNameAndTimestampGreaterThanEqualAndTimestampLessThanOrderByTimestampAsc(String symbolName, OffsetDateTime startTimestamp, OffsetDateTime endTimestamp);

  Flux<Tick> findAllBySymbolNameAndTimestampGreaterThanEqualOrderByTimestampAsc(String symbolName, OffsetDateTime startTimestamp);
}
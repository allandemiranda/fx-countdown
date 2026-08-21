package br.allandemiranda.fx.robot.repository.core;

import br.allandemiranda.fx.robot.model.core.impl.TickEntity;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TickRepository extends ReactiveCrudRepository<TickEntity, UUID> {

  Flux<TickEntity> findAllBySymbolNameAndTimestampGreaterThanEqualAndTimestampLessThanOrderByTimestampAsc(String symbolName, OffsetDateTime startTimestamp, OffsetDateTime endTimestamp);

  Flux<TickEntity> findAllBySymbolNameOrderByTimestampAsc(String symbolName);

  Mono<TickEntity> findBySymbolNameAndTimestamp(String symbolName, OffsetDateTime timestamp);

  Mono<TickEntity> findFirstBySymbolNameOrderByTimestampAsc(String symbolName);

  Mono<TickEntity> findFirstBySymbolNameOrderByTimestampDesc(String symbolName);

  Mono<TickEntity> findFirstTickEntityBySymbolNameOrderByTimestampDesc(String symbolName);
}

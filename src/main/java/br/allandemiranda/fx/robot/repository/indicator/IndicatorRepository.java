package br.allandemiranda.fx.robot.repository.indicator;

import br.allandemiranda.fx.robot.model.indicator.provider.Indicator;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface IndicatorRepository<M extends Indicator> extends ReactiveCrudRepository<M, UUID> {

  Mono<Void> deleteAllByEaName(String eaName);

  Flux<M> findAllByEaNameOrderByTimestampAsc(String eaName);

  Mono<M> findByEaNameAndTimestamp(String eaName, OffsetDateTime timestamp);

  Mono<M> findFirstByEaNameOrderByTimestampAsc(String eaName);

  Mono<M> findFirstByEaNameOrderByTimestampDesc(String eaName);
}

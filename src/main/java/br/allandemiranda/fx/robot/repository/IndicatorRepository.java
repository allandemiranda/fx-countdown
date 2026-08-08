package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IndicatorModel;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface IndicatorRepository<M extends IndicatorModel> extends ReactiveCrudRepository<M, UUID> {

  Mono<M> findByExpertAdvisorIdAndTimestamp(UUID expertAdvisorId, OffsetDateTime timestamp);

  Flux<M> findAllByExpertAdvisorIdOrderByTimestampAsc(UUID expertAdvisorId);

  Mono<Void> deleteAllByExpertAdvisorId(UUID expertAdvisorId);

  Mono<M> findFirstByExpertAdvisorIdOrderByTimestampAsc(UUID expertAdvisorId);

  Mono<M> findFirstByExpertAdvisorIdOrderByTimestampDesc(UUID expertAdvisorId);

  Flux<M> findAllByExpertAdvisorIdAndTimestampLessThanEqualOrderByTimestampDesc(UUID expertAdvisorId, OffsetDateTime timestamp, Pageable pageable);
}

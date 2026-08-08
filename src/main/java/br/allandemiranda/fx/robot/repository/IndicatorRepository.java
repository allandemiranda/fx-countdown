package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IndicatorModel;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface IndicatorRepository<M extends IndicatorModel> extends ReactiveCrudRepository<M, UUID> {

  Mono<M> findByDashboardIdAndTimestamp(UUID dashboardId, OffsetDateTime timestamp);

  Flux<M> findAllByDashboardIdAsc(UUID dashboardId);

  Mono<Void> deleteAllByDashboardId(UUID dashboardId);
}

package br.allandemiranda.fx.robot.repository.contract;

import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface ChartObjectRepository<M extends ChartObjectModel> extends ReactiveCrudRepository<M, UUID> {

  Mono<M> findByChartIdAndTimestamp(UUID chartId, OffsetDateTime timestamp);

  Flux<M> findAllByChartId(UUID chartId);

  Mono<Void> deleteAllByChartId(UUID chartId);
}

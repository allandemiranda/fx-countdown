package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.ChartObjectModel;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.UUID;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface ChartObjectRepository<M extends ChartObjectModel> extends ReactiveCrudRepository<M, UUID> {

  Mono<M> findByChartIdAndTimestamp(UUID chartId, OffsetDateTime timestamp);

  Flux<M> findAllByChartIdOrderByTimestampAsc(UUID chartId);

  Mono<Void> deleteAllByChartId(UUID chartId);

  Flux<M> findTop3ByChartIdAndTimestampLessThanEqualOrderByTimestampDesc(UUID chartId, OffsetDateTime timestamp);

  default Flux<M> getThreeConsecutiveRecordsAsc(UUID chartId, OffsetDateTime timestamp) {
    return findTop3ByChartIdAndTimestampLessThanEqualOrderByTimestampDesc(chartId, timestamp)
        .collectList()
        .flatMapMany(list -> {
          Collections.reverse(list);
          return Flux.fromIterable(list);
        });
  }
}

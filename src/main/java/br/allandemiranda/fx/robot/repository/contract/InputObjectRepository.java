package br.allandemiranda.fx.robot.repository.contract;

import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface InputObjectRepository<M extends InputObjectModel> extends ReactiveCrudRepository<M, UUID> {

  Mono<M> findByChartId(UUID chartId);

  Mono<Void> deleteByChartId(UUID chartId);
}

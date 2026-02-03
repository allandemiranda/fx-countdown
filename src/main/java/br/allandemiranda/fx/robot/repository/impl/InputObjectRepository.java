package br.allandemiranda.fx.robot.repository.impl;

import br.allandemiranda.fx.robot.model.InputObjectModel;
import java.util.UUID;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface InputObjectRepository<M extends InputObjectModel> extends ReactiveCrudRepository<M, UUID> {

  Mono<M> findByChartId(UUID chartId);

  Mono<Void> deleteByChartId(UUID chartId);
}

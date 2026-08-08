package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.InputDashboardModel;
import java.util.UUID;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface InputDashboardRepository<M extends InputDashboardModel> extends ReactiveCrudRepository<M, UUID> {

  Mono<M> findByDashboardId(UUID dashboardId);

  Mono<Void> deleteByDashboardId(UUID dashboardId);
}

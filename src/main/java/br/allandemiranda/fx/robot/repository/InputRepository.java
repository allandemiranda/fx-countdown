package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.InputModel;
import java.util.UUID;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface InputRepository<M extends InputModel> extends ReactiveCrudRepository<M, UUID> {

  Mono<M> findByExpertAdvisorId(UUID expertAdvisorId);

  Mono<Void> deleteByExpertAdvisorId(UUID expertAdvisorId);
}

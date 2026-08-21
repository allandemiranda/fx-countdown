package br.allandemiranda.fx.robot.repository.input;

import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.util.UUID;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface InputRepository<M extends Input> extends ReactiveCrudRepository<M, UUID> {

  Mono<Void> deleteByEaName(String eaName);

  Mono<M> findByEaName(String eaName);
}

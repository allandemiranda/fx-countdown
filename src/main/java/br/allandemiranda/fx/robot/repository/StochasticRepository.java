package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Stochastic;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StochasticRepository extends ReactiveCrudRepository<Stochastic, UUID> {

}
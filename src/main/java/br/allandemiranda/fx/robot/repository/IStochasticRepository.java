package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IStochastic;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IStochasticRepository extends ReactiveCrudRepository<IStochastic, UUID> {

}
package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IMASlow;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IMASlowRepository extends ReactiveCrudRepository<IMASlow, UUID> {

}
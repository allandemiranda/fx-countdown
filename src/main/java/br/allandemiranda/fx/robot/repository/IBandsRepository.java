package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IBands;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IBandsRepository extends ReactiveCrudRepository<IBands, UUID> {

}
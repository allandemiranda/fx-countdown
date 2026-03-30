package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.RSI;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RSIRepository extends ReactiveCrudRepository<RSI, UUID> {

}
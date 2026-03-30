package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.ATR;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ATRRepository extends ReactiveCrudRepository<ATR, UUID> {

}
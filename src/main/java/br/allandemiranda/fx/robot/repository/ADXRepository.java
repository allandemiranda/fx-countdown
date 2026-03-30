package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.ADX;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ADXRepository extends ReactiveCrudRepository<ADX, UUID> {

}
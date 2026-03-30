package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.GarchInput;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GarchInputRepository extends ReactiveCrudRepository<GarchInput, UUID> {

}
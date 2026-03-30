package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MaFast;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MaFastRepository extends ReactiveCrudRepository<MaFast, UUID> {

}
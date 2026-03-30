package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IRSI;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IRSIRepository extends ReactiveCrudRepository<IRSI, UUID> {

}
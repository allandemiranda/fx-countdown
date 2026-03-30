package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IADX;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IADXRepository extends ReactiveCrudRepository<IADX, UUID> {

}
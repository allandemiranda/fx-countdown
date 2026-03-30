package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IATR;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IATRRepository extends ReactiveCrudRepository<IATR, UUID> {

}